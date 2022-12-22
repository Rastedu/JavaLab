package UI;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class FileManager {

    int row=8;
    int col=8;
    byte[][] n = new byte[row][col];
    boolean[][] m = new boolean[row][col];

    public void WriteFile(byte[][] x, boolean[][] y,String filename) {
        try {
            File file = new File(filename);
            if(!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            try {
                for(int i=0;i<row;i++)
                    for(int j=0;j<col;j++)
                        pw.print(x[i][j]);

                for(int i=0;i<row;i++)
                    for(int j=0;j<col;j++)
                        pw.print(y[i][j]);
            } finally {pw.close();}
        } catch (IOException e) {
            System.out.println("Error: " +e);
        }

    }

    public float ReadFile(String filename) {
        try(FileWriter writer = new FileWriter(filename)){
            for(int i=0;i<row;i++) {
                for (int j = 0; j < col; j++) {
                    writer.write(n[i][j]);
                }
                writer.write("\r\n");
            }
            for(int i=0;i<row;i++) {
                for (int j = 0; j < col; j++) {
                    writer.write(String.valueOf(m[i][j]));
                }
                writer.write("\r\n");
            }
        } catch (Exception e) {
            System.out.println("Error:"+ e);
            return 0;
        }
        return 1;
    }
}
