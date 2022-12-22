package UI;

import control.PuzzleButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Filter;

public class BoardDrawUI extends JPanel {
    private int width;
    private int height;
    private JPanel panel;
    private ArrayList <Point> solution;
    private ArrayList <PuzzleButton> buttons;
    private BufferedImage source;
    private BufferedImage resized;
    private int row = 7, col = 7;

    private final int DESIRED_WIDTH = 400;

    private Image image;

    public void initUI(){
        solution = new ArrayList<>();
        for (int i=0; i < row; i++)
            for(int j=0; j < col; j++)
                solution.add(new Point(i,j));

        buttons = new ArrayList<>();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        panel.setLayout(new GridLayout(4,3));

        try{
            source = loadImage();
            int h = GetNewHeight(source.getWidth(),source.getHeight());
            resized = resizeImage(source, DESIRED_WIDTH,h ,BufferedImage.TYPE_INT_ARGB);
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        width = resized.getWidth();
        height = resized.getHeight();

        add(panel, BorderLayout.CENTER);
        for(int i = 0; i < row; i++)
            for(int j = 0; j < col; j++) {
                image = createImage(new FilteredImageSource(resized.getSource(), new CropImageFilter(j * width / row, i * height / col, width / row, height / col)));

                PuzzleButton button = new PuzzleButton(image);
                button.putClientProperty("position", new Point(i, j));
            }
    }

    private int GetNewHeight(int w, int h){
        double ratio = DESIRED_WIDTH / (double) w;
        int newHeight = (int) (h * ratio);
        return newHeight;
    }

    private BufferedImage resizeImage(BufferedImage originImage, int width,int height, int type){
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originImage,0,0,width,height,null);
        g.dispose();
        return resizedImage;
    }

    private BufferedImage loadImage() throws IOException {
        BufferedImage bimg = ImageIO.read(new File("res/board66.png"));
        return bimg;
    }

    /*
    private void drawGrid(Graphics2D g) {
        for (int i = 0; i < tiles.length; i++) {
            // we convert 1D coords to 2D coords given the size of the 2D Array
            int r = i / size;
            int c = i % size;
            // we convert in coords on the UI
            int x = margin + c * tileSize;
            int y = margin + r * tileSize;

            // check special case for blank tile
            if(tiles[i] == 0) {
                if (gameOver) {
                    g.setColor(FOREGROUND_COLOR);
                    drawCenteredString(g, "\u2713", x, y);
                }

                continue;
            }

            // for other tiles
            g.setColor(getForeground());
            g.fillRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(Color.BLACK);
            g.drawRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(Color.WHITE);

            drawCenteredString(g, String.valueOf(tiles[i]), x , y);
        }
    }

     */

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int size = Math.min(getWidth() - 4, getHeight() - 4) / 10; // Изменяемое число размера сетки
        int width = getWidth() - (size * 2);
        int height = getHeight() - (size * 2);

        int y = (getHeight() - (size * 10)) / 2;
        for (int horz = 0; horz < 10; horz++) {
            int x = (getWidth() - (size * 10)) / 2;
            for (int vert = 0; vert < 10; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        g2d.dispose();
    }
}
