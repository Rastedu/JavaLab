package control;

import UI.GraphicApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu {
    private JFrame frame;
    GraphicApp graphicApp;
    ChooseSourceOfLevel editor;

    ImageIcon image2;
    Toolkit toolkit = Toolkit.getDefaultToolkit();


    public Menu() {
        CreateFrame();
        frame.setLocationRelativeTo(null);
        JPanel panel = MenuPanel();
        frame.add(panel, BorderLayout.CENTER);
    }

    private void CreateFrame() {
        frame = new JFrame("Daily tracks");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JPanel MenuPanel() {
        JPanel menupanel = new JPanel();
        menupanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        menupanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        menupanel.add(new JLabel("<html><h1><strong><i>Daily Tracks</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        JButton button1 = new JButton("Start");
        button1.setBackground(Color.white);
        button1.setFont(new Font("TimesRoman", Font.BOLD, 15));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        buttons.add(button1, gbc);
        JButton button2 = new JButton("Editor");
        button2.setBackground(Color.white);
        button2.setFont(new Font("TimesRoman", Font.BOLD, 15));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        buttons.add(button2, gbc);
        JButton button3 = new JButton("How to play");
        button3.setBackground(Color.white);
        button3.setFont(new Font("TimesRoman", Font.BOLD, 15));
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button3MouseClicked(e);
            }
        });
        buttons.add(button3, gbc);
        JButton button4 = new JButton("Exit");
        button4.setBackground(Color.white);
        button4.setFont(new Font("TimesRoman", Font.BOLD, 15));
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button4MouseClicked(e);
            }
        });
        buttons.add(button4, gbc);

        gbc.weighty = 1;
        menupanel.add(buttons, gbc);
        return menupanel;
    }

    private void button1MouseClicked(MouseEvent e) {
        frame.dispose();
        editor = new ChooseSourceOfLevel();
        editor.show();
    }

    private void button2MouseClicked(MouseEvent e) {
        frame.dispose();
        graphicApp = new GraphicApp(true);
        graphicApp.show();
    }

    /**
     * @param parentComponent
     * @param message
     * @param title
     * @param messageType
     * @param icon
     * @throws HeadlessException
     */
    public static void showMessageDialog(Component parentComponent,
                                         Object message,
                                         String title,
                                         int messageType,
                                         Icon icon)
            throws HeadlessException{}

    private void button3MouseClicked(MouseEvent e) {
        String text = """
                Построй путь от начала до конца таким образом,
                 чтобы в строках и столбцах было столько сегментов,
                 сколько указано в левой и верхней частей поля.
                 Вам доступны прямые сегменты пути и секции\s
                 поворотов на 90 градусов; путь не может\s
                 пересекать сам себя. Прокладывайте путь по последовательности плиток.""";
        JTextArea textArea = new JTextArea(text);
        textArea.setColumns(30);
        textArea.setLineWrap( true );
        textArea.setWrapStyleWord( true );
        textArea.setSize(textArea.getPreferredSize().width, 1);
        Font font = new Font("Verdana", Font.BOLD, 12);
        textArea.setFont(font);
        JOptionPane pane = new JOptionPane();
        image2 = new ImageIcon("res/Rool.jpg");
        JOptionPane.showMessageDialog(pane,textArea, "Правила игры Daily tracks", JOptionPane.CLOSED_OPTION,image2);

    }

    private void button4MouseClicked(MouseEvent e) {
        unshow();
    }

    public void unshow() {
        frame.dispose();
    }

    public void show() {
        frame.setVisible(true);
    }


}
