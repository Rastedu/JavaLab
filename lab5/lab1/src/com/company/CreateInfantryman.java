package com.company;

public class CreateInfantryman extends CreateWarrior{
    @Override
    protected Infantryman makeWarrior(int id) {
        return new Infantryman();
    }
    protected int getStrength(){
        return new Infantryman().returnStrength();
    }
}
