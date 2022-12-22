package control;

import UI.GraphicApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ChooseSourceOfLevel {
    private JFrame frame;
    GraphicApp graphicApp;


    public ChooseSourceOfLevel() {
        CreateFrame();
        frame.setLocationRelativeTo(null);
        JPanel panel = MenuPanel();
        frame.add(panel, BorderLayout.CENTER);
    }

    private void CreateFrame() {
        frame = new JFrame("Daily tracks");
        frame.setSize(300, 250);
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
        JButton button1 = new JButton("Get level from file");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        buttons.add(button1, gbc);

        JButton button2 = new JButton("Get default level");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        buttons.add(button2, gbc);

        gbc.weighty = 1;
        menupanel.add(buttons, gbc);
        return menupanel;
    }

    private void button1MouseClicked(MouseEvent e) {
        System.out.println("choose from file!");
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Only .txt", "txt");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }

        byte[][] checksums = new byte[8][8];
        int[][] checkNews = new int[8][8];
        boolean second_array = false;
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() < 2)
                    second_array = true;
                else if (!second_array){
                    var x = line.split(" ");
                    for (int i = 0; i < x.length; i++) {
                        checksums[index][i] = Byte.parseByte(x[i]);
                    }
                    index++;
                } else {
                    var x = line.split(" ");
                    for (int i = 0; i < x.length; i++) {
                        checkNews[index % 8][i] = Byte.parseByte(x[i]);
                    }
                    index++;
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        frame.dispose();
        graphicApp = new GraphicApp(checksums, checkNews);
        //graphicApp.SetGuess(checksums, checkNews);
        graphicApp.show();
    }

    private void button2MouseClicked(MouseEvent e) {
        System.out.println("get default level");
        frame.dispose();
        graphicApp = new GraphicApp();
        graphicApp.show();
    }
    public void unshow() {
        frame.dispose();
    }

    public void show() {
        frame.setVisible(true);
    }


}
