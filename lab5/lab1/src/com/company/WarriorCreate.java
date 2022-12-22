package com.company;

public class WarriorCreate extends Notifier {
    Game game = new Game();
    public WarriorCreate(int priority){
        super(priority);
    }

    @Override
    public void write(String message) {
        System.out.print(" ");
        game.recruitWarrior();
        System.out.println(" " + message);
    }


}
