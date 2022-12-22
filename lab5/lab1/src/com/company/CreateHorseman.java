package com.company;

public class CreateHorseman extends CreateWarrior{
    @Override
        protected Horseman makeWarrior(int id) {
        return new Horseman();
    }
    protected int getStrength(){
        return new Horseman().returnStrength();
    }
}

