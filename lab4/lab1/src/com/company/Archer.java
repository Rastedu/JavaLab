package com.company;

public class Archer implements Warrior{
    @Override
    public void info(Warrior warrior, int id) {
        System.out.println("Archer["+ id +"]");
    }
    @Override
    public int returnStrength() {
        return 2;
    }
}
