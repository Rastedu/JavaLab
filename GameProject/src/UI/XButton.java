package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import control.Logic;

import static UI.GraphicApp.col;
import static UI.GraphicApp.row;

public class XButton extends JButton implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    ImageIcon Track1, Track2, Track3, Track4, Track5, Track6, Track7, Track8;
    ImageIcon STrack1, STrack2, STrack3, STrack4, STrack5, STrack6;
    private byte value = 0;

    private boolean is_active = true;

    private boolean isNone = false;

    public byte getValue() {
        return value;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    int xi, yj;

    private CellStatus status;

    public CellStatus getStatus() {
        return status;
    }


    public void setStatus(CellStatus status) {
        this.status = status;
    }

    GraphicApp graphicApp;
    byte num;
    Logic logic;

    public XButton(int xi, int yj, GraphicApp ga) {
        super();
        this.xi = xi;
        this.yj = yj;
        this.graphicApp = ga;
        Track1 = new ImageIcon("res/resizedtracks/track1-1.png");
        Track2 = new ImageIcon("res/resizedtracks/track2-1.png");
        Track3 = new ImageIcon("res/resizedtracks/track3-1.png");
        Track4 = new ImageIcon("res/resizedtracks/track41.png");
        Track5 = new ImageIcon("res/resizedtracks/track5-1.png");
        Track6 = new ImageIcon("res/resizedtracks/track6-1.png");
        Track7 = new ImageIcon("res/resizedtracks/not-1.png");
        Track8 = new ImageIcon("res/resizedtracks/track-any1.png");
        STrack1 = new ImageIcon("res/static/st-track1.png");
        STrack2 = new ImageIcon("res/static/st-track2.png");
        STrack3 = new ImageIcon("res/static/st-track3.png");
        STrack4 = new ImageIcon("res/static/st-track4.png");
        STrack5 = new ImageIcon("res/static/st-track5.png");
        STrack6 = new ImageIcon("res/static/st-track6.png");
        this.addActionListener(this);
        this.addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);

    }

    public int GetRow() {
        return this.xi;
    }

    public int GetCol() {
        return this.yj;
    }

    public int GetValue() {
        return value;
    }

    public CellStatus GetStatus() {
        return this.status;
    }

    public void SetRow(int xi) {
        this.xi = xi;
    }

    public void SetCol(int yj) {
        this.yj = yj;
    }


    public void actionPerformed(ActionEvent e) {

        System.out.println("value before: " + value);
        if (this.value == 0 && !isNone) {
            this.value = (byte) (graphicApp.cd.value_of_button + 1);
            isNone = true;
        } else {
            this.value++;
            if (value > 9) value = 0;
        }
        System.out.println("value after: " + value);

        if (graphicApp == null)
            graphicApp = new GraphicApp(this);

        switch (value) {
            case 0 -> {
                setIcon(null);
            }
            case 1 -> {
                setIcon(Track1);
            }
            case 2 -> {
                setIcon(Track2);
            }
            case 3 -> {
                setIcon(Track3);
            }
            case 4 -> {
                setIcon(Track4);
            }
            case 5 -> {
                setIcon(Track5);
            }
            case 6 -> {
                setIcon(Track6);
            }
            case 7 -> {
                setIcon(Track7);
            }
            case 8 -> {
                setIcon(Track8);
            }
        }
        if (this.num == value) {
            setStatus(CellStatus.CORRECT_GUESS);
            this.status = CellStatus.CORRECT_GUESS;
        } else {
            setStatus(CellStatus.WRONG_GUESS);
        }
        GetValue();
        GetStatus();
        SetButton();

        if (graphicApp.Solve()) {
            logic = new Logic();
            logic.displayVictory(graphicApp);
        }
    }

    public void SetVariable(byte num, int isGiven, int xi, int yj) {
        this.xi = xi;
        this.yj = yj;
        this.num = num;
        if (isGiven == 0) status = CellStatus.GIVEN;
        if (isGiven == 1) status = CellStatus.TO_GUESS;
        if (isGiven == 2) status = CellStatus.WRONG_GUESS;
        if (isGiven == 3) status = CellStatus.CORRECT_GUESS;
        SetButton();    // paint itself
    }


    public void SetButton() {
        if (status == CellStatus.GIVEN) {
            if (num == 0) setIcon(null);
            STrack1 = new ImageIcon("res/static/st-track1.png");
            if (num == 1) setIcon(STrack1);
            STrack2 = new ImageIcon("res/static/st-track2.png");
            if (num == 2) setIcon(STrack2);
            STrack3 = new ImageIcon("res/static/st-track3.png");
            if (num == 3) setIcon(STrack3);
            STrack4 = new ImageIcon("res/static/st-track4.png");
            if (num == 4) setIcon(STrack4);
            STrack5 = new ImageIcon("res/static/st-track5.png");
            if (num == 5) setIcon(STrack5);
            STrack6 = new ImageIcon("res/static/st-track6.png");
            if (num == 6) setIcon(STrack6);

            super.setEnabled(false);
        } else if (status == CellStatus.TO_GUESS) {
            super.setEnabled(true);
            Track1 = new ImageIcon("res/resizedtracks/track1-1.png");
            Track2 = new ImageIcon("res/resizedtracks/track2-1.png");
            Track3 = new ImageIcon("res/resizedtracks/track3-1.png");
            Track4 = new ImageIcon("res/resizedtracks/track41.png");
            Track5 = new ImageIcon("res/resizedtracks/track5-1.png");
            Track6 = new ImageIcon("res/resizedtracks/track6-1.png");
            Track7 = new ImageIcon("res/resizedtracks/not-1.png");
            Track8 = new ImageIcon("res/resizedtracks/track-any1.png");
        }
        if (status == CellStatus.CORRECT_GUESS) {  // from TO_GUESS
            super.setBackground(Color.GREEN);

            this.graphicApp.buttons[xi][yj].value = (byte) this.graphicApp.fields_to_change[xi][yj];
        }
        if (status == CellStatus.WRONG_GUESS) {    // from TO_GUESS
            super.setBackground(Color.YELLOW);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //XButton xButton = (XButton) e.getSource();

        graphicApp = new GraphicApp();
        logic = new Logic();

        if (code == KeyEvent.VK_1) {
            this.value = 1;
            if (num == value) status = CellStatus.CORRECT_GUESS;
            else {
                status = CellStatus.WRONG_GUESS;
            }
            setIcon(Track1);
            SetButton();
            if (graphicApp.Solve()){
                logic.displayVictory(graphicApp);
            }
        }
        if (code == KeyEvent.VK_2) {
            this.value = 2;
            if (num == value) status = CellStatus.CORRECT_GUESS;
            else {
                status = CellStatus.WRONG_GUESS;
            }
            setIcon(Track2);
            SetButton();
            if (graphicApp.Solve()){
                logic.displayVictory(graphicApp);
            }
        }
        if (code == KeyEvent.VK_3) {
            this.value = 3;
            if (num == value) status = CellStatus.CORRECT_GUESS;
            else {
                status = CellStatus.WRONG_GUESS;
            }
            setIcon(Track3);
            SetButton();
            if (graphicApp.Solve()){
                logic.displayVictory(graphicApp);
            }
        }
        if (code == KeyEvent.VK_4) {
            this.value = 4;
            if (num == value) status = CellStatus.CORRECT_GUESS;
            else {
                status = CellStatus.WRONG_GUESS;
            }
            setIcon(Track4);
            SetButton();
            if (graphicApp.Solve()){
                logic.displayVictory(graphicApp);
            }
        }
        if (code == KeyEvent.VK_5) {
            this.value = 5;
            if (num == value) status = CellStatus.CORRECT_GUESS;
            else {
                status = CellStatus.WRONG_GUESS;
            }
            setIcon(Track5);
            SetButton();
            if (graphicApp.Solve()){
                logic.displayVictory(graphicApp);
            }
        }
        if (code == KeyEvent.VK_6) {
            this.value = 6;
            if (num == value) status = CellStatus.CORRECT_GUESS;
            else {
                status = CellStatus.WRONG_GUESS;
            }
            setIcon(Track6);
            SetButton();
            if (graphicApp.Solve()){
                logic.displayVictory(graphicApp);
            }
        }
        if (code == KeyEvent.VK_7) {
            this.value = 7;
            if (num == value) status = CellStatus.CORRECT_GUESS;
            else {
                status = CellStatus.WRONG_GUESS;
            }
            setIcon(Track7);
            SetButton();
            if (graphicApp.Solve()){
                logic.displayVictory(graphicApp);
            }
        }
        if (code == KeyEvent.VK_8) {
            this.value = 8;
            if (num == value) status = CellStatus.CORRECT_GUESS;
            else {
                status = CellStatus.WRONG_GUESS;
            }
            setIcon(Track8);
            SetButton();
            if (graphicApp.Solve()){
                logic.displayVictory(graphicApp);
            }
        }
        if (code == KeyEvent.VK_0) {
            this.value = 0;
            if (num == value) status = CellStatus.CORRECT_GUESS;
            else {
                status = CellStatus.WRONG_GUESS;
            }
            setIcon(null);
            if (graphicApp.Solve()){
                logic.displayVictory(graphicApp);
            }
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_1) {
            this.value = 1;
            setIcon(STrack1);
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_2) {
            this.value = 2;
            setIcon(STrack2);
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_3) {
            this.value = 3;
            setIcon(STrack3);

        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_4) {
            this.value = 4;
            setIcon(STrack4);

        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_5) {
            this.value = 5;
            setIcon(STrack5);
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_6) {
            this.value = 6;
            setIcon(STrack6);

        }





        // //// //

        if (code == KeyEvent.VK_UP) {
            if (GetRow() == row) SetRow(0);
            else {
                SetRow(GetRow() + 1);
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            int col1;
            if (GetRow() == 0) SetRow(row);
            else {
                SetRow(GetRow() - 1);
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            int row1;
            if (GetCol() == col) SetCol(0);
            else {
                SetCol(GetCol() + 1);
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            int row1;
            if (GetCol() == 0) SetCol(col);
            else {
                SetCol(GetCol() - 1);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(BorderFactory.createLineBorder(Color.yellow)); // желтая рамка вокруг выбранного тайла
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(BorderFactory.createLineBorder(Color.gray)); // изменение рамки с желтой на серую
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void addActionListener(GraphicApp graphicApp) {
        this.addActionListener(this);
    }

    public void addMouseListener(GraphicApp graphicApp) {
        this.addMouseListener(this);
    }

    public void addKeyListener(GraphicApp graphicApp) {
        this.addKeyListener(this);
    }

    public void addMouseMotionListener(GraphicApp graphicApp) {
        this.addMouseMotionListener(this);
    }
}
