package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import control.Menu;

public class GraphicApp {
    private JFrame frame;
    String imagePath = "res/title_small2.png";
    BufferedImage myPicture;
    public static int row = 8;
    public static int col = 8;

    ImageIcon STrack1, STrack2, STrack3, STrack4, STrack5, STrack6;

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image1 = toolkit.getImage("res/resizedtracks/track1-1.png");
    Image image2 = toolkit.getImage("res/resizedtracks/track2-1.png");
    Image image3 = toolkit.getImage("res/resizedtracks/track3-1.png");
    Image image4 = toolkit.getImage("res/resizedtracks/track41.png");
    Image image5 = toolkit.getImage("res/resizedtracks/track5-1.png");
    Image image6 = toolkit.getImage("res/resizedtracks/track6-1.png");
    Image image7 = toolkit.getImage("res/resizedtracks/not-1.png");
    Image image8 = toolkit.getImage("res/resizedtracks/track-any1.png");
    Image image9 = toolkit.getImage("res/resizedtracks/track-empty1.png");
    Image image = image9;

    JPanel p = new JPanel() {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(58 * row, 58 * col);
        }
    };
    XButton[][] buttons = new XButton[row][col];

    byte[][] byte_checksums = new byte[row][col];

    int[][] fields_to_change = new int[row][col];

    public boolean fromFile = false;

    public void SetGuess() {

        byte[][] finalValuesInLevel = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 5, 1, 0, 0, 0},
                {0, 0, 2, 1, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        for (int i = 0; i < row; i++)
            if (col >= 0) System.arraycopy(finalValuesInLevel[i], 0, byte_checksums[i], 0, col);

        int[][] WhatToChange = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        for (int i = 0; i < row; i++)
            if (col >= 0) System.arraycopy(WhatToChange[i], 0, fields_to_change[i], 0, col);
    }

    XButton button;

    public ComponentDrag cd; // was package-private
    private Boolean display_style_buttons = null;

    {
        try {
            myPicture = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GraphicApp() {
        createFrame();
        initPanel();
        fromFile = false;
    }

    public GraphicApp(Boolean disp) {
        display_style_buttons = disp;
        if (disp){
            // clear field
            for (byte[] byte_checksum : byte_checksums) {
                Arrays.fill(byte_checksum, (byte) 0);
            }
            for (int[] fields : fields_to_change) {
                Arrays.fill(fields, (int) 1);
            }
        }
        createFrame();
        initPanel();
        fromFile = false;
    }

    public GraphicApp(byte[][] byte_checksums, int[][] int_news) {
        this.fromFile = true;
        this.byte_checksums = byte_checksums;
        this.fields_to_change = int_news;
        createFrame();
        initPanel();
    }

    public GraphicApp(XButton xButton) {
        button = xButton;
        createFrame();
        initPanel();
    }

    private void createFrame() {
        frame = new JFrame("Daily Tracks");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initPanel() {
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        JPanel rng = new JPanel();
        JPanel center = new JPanel();

        Box rng1 = createRightPanel();
        mainContainer.add(rng1, BorderLayout.EAST);

        p.setLayout(new GridLayout(8, 8));
        p.setPreferredSize(new Dimension(58 * row, 58 * col));
        initButtons();
        //jtable = new JTable(tabelModel);
        mainContainer.add(p, BorderLayout.CENTER);
    }

    public Box createRightPanel() {
        Box panel = Box.createVerticalBox();
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        panel.add(picLabel);
        panel.add(Box.createVerticalStrut(20)); // отступ;
        cd = new ComponentDrag(frame);
        D(panel, image1, image2, image3);

        D(panel, image4, image5, image6);

        D(panel, image7, image8, image9);


        ButtonGroup bGrup = new ButtonGroup();

        JButton pause = new JButton("Пауза"); // Кнопка
        pause.setFont(new Font("TimesRoman", Font.BOLD, 15));
        bGrup.add(pause);
        panel.add(pause);
        panel.add(Box.createVerticalStrut(20)); // отступ;

        JButton menu = new JButton("Меню"); // Кнопка
        menu.setFont(new Font("TimesRoman", Font.BOLD, 15));
        menu.addActionListener(this::MenuClicked);

        bGrup.add(menu);
        panel.add(menu);
        panel.add(Box.createVerticalStrut(20)); // отступ;


        if (display_style_buttons != null && display_style_buttons) { // если кнопка изменения рельс уже включена - отобразить кнопку
            JButton RailwaysType = new JButton("Сменить тип рельс"); // Кнопка
            RailwaysType.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            bGrup.add(RailwaysType);
            panel.add(RailwaysType);

            panel.add(Box.createVerticalStrut(20));

            JButton Save = new JButton("Сохранить карту"); // Кнопка
            Save.setFont(new Font("TimesRoman", Font.BOLD, 10));
            Save.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        button2MouseClicked(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            bGrup.add(Save);
            panel.add(Save);
        }
        panel.add(Box.createVerticalStrut(20));
        return panel;
    }

    public void initButtons() {
        System.out.println("from file: " + fromFile);
        if (!fromFile && !display_style_buttons)
            SetGuess();

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                buttons[i][j] = new XButton(i, j, this);
                if (i == 0) {
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setText(String.valueOf(j)); //value of count
                }
                if (j == 0) {
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setText(String.valueOf(i)); //value of j
                }
                if (i % 2 == 0) {
                    if (j % 2 == 0) buttons[i][j].setBackground(Color.WHITE);
                    if (j % 2 == 1) buttons[i][j].setBackground(Color.GRAY);
                }
                if (i % 2 == 1) {
                    if (j % 2 == 0) buttons[i][j].setBackground(Color.GRAY);
                    if (j % 2 == 1) buttons[i][j].setBackground(Color.WHITE);
                }
                buttons[i][j].SetVariable(byte_checksums[i][j], fields_to_change[i][j], i, j);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.gray));
                buttons[i][j].setPreferredSize(new Dimension(58, 58));

                p.add(buttons[i][j]);
            }

    }

    public boolean Solve() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (buttons[i][j].getStatus() == CellStatus.TO_GUESS || buttons[i][j].getStatus() == CellStatus.WRONG_GUESS)
                    return false;
            }
        }
        return true;
    }

    public int GetRow() {
        return row;
    }

    public int GetCol() {
        return col;
    }

    public void SetRow(int row) {
        GraphicApp.row = row;
    }

    public void SetCol(int col) {
        GraphicApp.col = col;
    }

    private void D(Box panel, Image image1, Image image2, Image image3) {
        Box panel1 = Box.createHorizontalBox();
        JButton button1 = new JButton(new ImageIcon(image1));
        button1.setPreferredSize(new Dimension(58, 58));
        button1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button1.setBackground(Color.WHITE);
        cd.registerComponent(button1);

        JButton button2 = new JButton(new ImageIcon(image2));
        button2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button2.setPreferredSize(new Dimension(58, 58));
        button2.setBackground(Color.WHITE);
        cd.registerComponent(button2);

        JButton button3 = new JButton(new ImageIcon(image3));
        button3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button3.setPreferredSize(new Dimension(58, 58));
        button3.setBackground(Color.WHITE);
        cd.registerComponent(button3);

        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel.add(panel1);
    }

    private void button1MouseClicked(MouseEvent e) {
        STrack1 = new ImageIcon("res/static/st-track1.png");
        STrack2 = new ImageIcon("res/static/st-track2.png");
        STrack3 = new ImageIcon("res/static/st-track3.png");
        STrack4 = new ImageIcon("res/static/st-track4.png");
        STrack5 = new ImageIcon("res/static/st-track5.png");
        STrack6 = new ImageIcon("res/static/st-track6.png");
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                this.buttons[i][j].setIs_active(false);
                if(this.buttons[i][j].getValue() == 1)buttons[i][j].setIcon(STrack1);
                if(this.buttons[i][j].getValue() == 2)buttons[i][j].setIcon(STrack2);
                if(this.buttons[i][j].getValue() == 3)buttons[i][j].setIcon(STrack3);
                if(this.buttons[i][j].getValue() == 4)buttons[i][j].setIcon(STrack4);
                if(this.buttons[i][j].getValue() == 5)buttons[i][j].setIcon(STrack5);
                if(this.buttons[i][j].getValue() == 6)buttons[i][j].setIcon(STrack6);
            }

    }

    private void button2MouseClicked(MouseEvent e) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Only .txt", "txt");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(chooser.getSelectedFile()));
        for (int i = 0; i < byte_checksums.length; i++) {
            for (int j = 0; j < byte_checksums[i].length; j++) {
//                System.out.println(i + ", " + j + " : " + buttons[i][j].getStatus() + " / " +
//                        buttons[i][j].getValue() + " / " + buttons[i][j].getIcon());

                writer.write(buttons[i][j].getValue()+"");
                if (j < byte_checksums[i].length-1)
                    writer.write(" ");
            }
            writer.write("\n");
        }
        writer.write("\n");

        for (int i = 0; i < fields_to_change.length; i++) {
            for (int j = 0; j < fields_to_change[i].length; j++) {

                int value = 0;
                if (buttons[i][j].getIcon() != null)
                    value = buttons[i][j].getIcon().toString().contains("static") ? 0 : 1;

                writer.write(value+"");
                if (j < fields_to_change[i].length-1)
                    writer.write(" ");
            }
            if (i < fields_to_change.length-1)
                writer.write("\n");
        }

        writer.close();

    }

    public void MenuClicked(ActionEvent e){
        frame.dispose();
        Menu menu = new Menu();
        menu.show();
    }

    public void unshow() {
        frame.dispose();
    }
    public void show() {
        frame.setVisible(true);
    }

}