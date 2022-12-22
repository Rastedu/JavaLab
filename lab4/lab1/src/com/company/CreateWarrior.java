package com.company;

import java.util.ArrayList;
import java.util.List;

public abstract class CreateWarrior {
    private final List<Warrior> warriors = new ArrayList<>();
    static int id=0;

    public CreateWarrior() {
        Warrior Archer = makeWarrior(id);
        Archer.info(Archer,id);
        warriors.add(Archer);
        id++;
    }
    protected abstract Warrior makeWarrior(int id);
    abstract protected int getStrength();
}