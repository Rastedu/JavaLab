package com.company;

public class CreateArcher extends CreateWarrior{
    @Override
    protected Archer makeWarrior(int id) {
        return new Archer();
    }
    protected int getStrength(){
        return new Archer().returnStrength();
    }
}
