package UI;

import control.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Editor {
    private JFrame frame;
    public static int row = 8;
    public static int col = 8;
    String imagePath = "res/title_small2.png";
    BufferedImage myPicture;

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

    ComponentDrag cd;

    {
        try {
            myPicture = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    JPanel p =new JPanel() {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(58*row, 58*col);
        }
    };
    XButton[][] buttons = new XButton[row][col];

    byte[][] checksumm = new byte[row][col];

    int[][] checks = new int[row][col];


    public void SetGuess(){

        byte[][] CheckNew = {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,5,1,0,0,0},
                {0,0,2,1,5,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        for(int i=0;i<row;i++)
            if (col >= 0) System.arraycopy(CheckNew[i], 0, checksumm[i], 0, col);

        int[][] CheckNew1 = {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };

        for (int i=0;i<row;i++)
            if (col >= 0) System.arraycopy(CheckNew1[i], 0, checks[i], 0, col);
    }

    public Editor(){
        createFrame();
        initPanel();
    }
    public Editor(XButton xButton){
        createFrame();
        initPanel();
    }

    private void createFrame() {
        frame = new JFrame("Daily Tracks");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initPanel(){
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        JPanel rng = new JPanel();
        JPanel center = new JPanel();

        Box rng1 = createRightPanel();
        mainContainer.add(rng1, BorderLayout.EAST);

        p.setLayout(new GridLayout(8,8));
        p.setPreferredSize(new Dimension(58*row,58*col));
        initButtons();
        //jtable = new JTable(tabelModel);
        mainContainer.add(p, BorderLayout.CENTER);
    }

    public Box createRightPanel(){
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
        bGrup.add(pause);
        panel.add(pause);
        panel.add(Box.createVerticalStrut(20)); // отступ;
        JButton menu = new JButton("Меню"); // Кнопка
        menu.addActionListener(this::MenuClicked1);
        bGrup.add(menu);
        panel.add(menu);
        panel.add(Box.createVerticalStrut(20)); // отступ;
        return panel;
    }

    public void initButtons(){
        int count = 0;
        SetGuess();
        for(int i=0; i<row; i++)
            for(int j=0;j<col;j++){
                buttons[i][j] = new XButton(i,j, new GraphicApp());
                if(i==0){
                    count++;
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setText(String.valueOf(count));
                }
                if(j==0){
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setText(String.valueOf(j));
                }
                if(i%2==0){
                    if(j%2==0)buttons[i][j].setBackground(Color.WHITE);
                    if(j%2==1)buttons[i][j].setBackground(Color.GRAY);
                }
                if(i%2==1){
                    if(j%2==0)buttons[i][j].setBackground(Color.GRAY);
                    if(j%2==1)buttons[i][j].setBackground(Color.WHITE);
                }
                System.out.println("in editor: " + i + "/" + j + " : " + checksumm[i][j] + ", " + checks[i][j]);
                buttons[i][j].SetVariable(checksumm[i][j],checks[i][j],i,j);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.gray));
                buttons[i][j].setPreferredSize(new Dimension(58,58));
                p.add(buttons[i][j]);
            }

    }
//
//    public boolean Solve(){
//        for(int i=0;i<row;i++)
//            for(int j=0;j<col;j++) {
//                if (this.buttons[i][j].num == this.buttons[i][j].getValue() && this.buttons[i][j].getStatus() == CellStatus.TO_GUESS) { this.checks[i][j] = 3;
//                    this.buttons[i][j].SetVariable(this.checksumm[i][j],this.checks[i][j],i,j);}
//                System.out.println(buttons[i][j].getStatus());
//                System.out.println(i+" "+  j+ " "+ buttons[i][j].num);
//                System.out.println(this.buttons[i][j].getValue());
//            }
//        for(int i=0;i<row;i++)
//            for(int j=0;j<col;j++)if (buttons[i][j].getStatus() == CellStatus.WRONG_GUESS || buttons[i][j].getStatus() == CellStatus.TO_GUESS)
//                return false;
//
//        return true;
//    }

    public int GetRow(){return row;}

    public int GetCol(){return col;}

    public void SetRow(int row){
        GraphicApp.row =row;}

    public void SetCol(int col){
        GraphicApp.col =col;}

    private void D(Box panel, Image image1, Image image2, Image image3) {
        Box panel1 = Box.createHorizontalBox();
        JButton button1 = new JButton(new ImageIcon(image1));
        button1.setPreferredSize(new Dimension(58,58));
        button1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button1.setBackground(Color.WHITE);
        cd.registerComponent(button1);

        JButton button2 = new JButton(new ImageIcon(image2));
        button2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button2.setPreferredSize(new Dimension(58,58));
        button2.setBackground(Color.WHITE);
        cd.registerComponent(button2);

        JButton button3 = new JButton(new ImageIcon(image3));
        button3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button3.setPreferredSize(new Dimension(58,58));
        button3.setBackground(Color.WHITE);
        cd.registerComponent(button3);

        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel.add(panel1);
    }

    public void MenuClicked1(ActionEvent e){
        frame.dispose();
        Menu menu = new Menu();
        menu.show();
    }

    public void show(){
        frame.setVisible(true);
    }
}
