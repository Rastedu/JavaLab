package com.company;

public class Infantryman implements Warrior{
    @Override
    public void info(Warrior warrior, int id) {
         System.out.println("Infantryman["+id+"]");
    }
    @Override
    public int returnStrength() {
        return 3;
    }
}
