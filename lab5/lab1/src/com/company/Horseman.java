package com.company;

public class Horseman implements Warrior {
    @Override
    public void info(Warrior warrior, int id) {
         System.out.println("Horseman["+id+"]");
    }
    @Override
    public int returnStrength() {
        return 5;
    }
}
