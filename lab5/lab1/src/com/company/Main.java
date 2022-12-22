package com.company;

public class Main {

    public static void main(String[] args) {
        CreateWarrior createArcher = new CreateArcher();
        CreateWarrior createHorseman = new CreateHorseman();
        CreateWarrior createInfantryman = new CreateInfantryman();
        createArcher.makeWarrior(createArcher.id);
        createHorseman.makeWarrior(createHorseman.id);
        createInfantryman.makeWarrior(createInfantryman.id);
    }
}
