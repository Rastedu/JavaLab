package control;

import UI.GraphicApp;

import javax.swing.*;


public class Logic {
    GraphicApp graphicApp;

    public void displayVictory(GraphicApp gr) {


        JOptionPane pane = new JOptionPane();
        int dialogResult = JOptionPane.showConfirmDialog(pane, "You won! Would you like to exit to menu?",
                "Game over!", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            gr.unshow();
            Menu m = new Menu();
            m.show();
        } else System.exit(0);


    }

    public boolean checkForWinner() {
        graphicApp = new GraphicApp();
        return graphicApp.Solve();
    }

    public void resetTheButtons() {
        graphicApp = new GraphicApp();
        for (int i = 0; i < GraphicApp.row; i++)
            for (int j = 0; j < GraphicApp.col; j++) {
                //buttons[i][j]=0;
            }
    }

    // обнуление массива
    public boolean checkDraw() {
        boolean full = true;
        for (int i = 0; i < GraphicApp.row; i++)
            for (int j = 0; j < GraphicApp.col; j++) {
                //if(buttons[i][j] == 0)full =false;
            }
        return full;
    }


}
