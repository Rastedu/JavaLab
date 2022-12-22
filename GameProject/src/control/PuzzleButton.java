package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PuzzleButton extends JButton {

    private boolean isLastButton;

    public PuzzleButton(){
        super();
        initUI();
    }
    public PuzzleButton(Image i){
        super(new ImageIcon(i));
        initUI();
    }

    private void initUI(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.yellow)); // желтая рамка вокруг выбранного тайла
            }

            @Override
            public void mouseExited(MouseEvent e){
                setBorder(BorderFactory.createLineBorder(Color.gray)); // изменение рамки с желтой на серую
            }
        });
    }

    public boolean isLastButton(){
        return isLastButton;

    }

    public void setLastButton(boolean isLastButton){
        this.isLastButton = isLastButton;
    }
}
