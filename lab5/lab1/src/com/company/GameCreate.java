package com.company;

public class GameCreate extends Notifier{

    Game game = new Game();
    public GameCreate(int priority){
        super(priority);
    }

    @Override
    public void write(String message) {
        System.out.print(" ");
        game.createGame();
        System.out.print(" " + message);
    }
}
