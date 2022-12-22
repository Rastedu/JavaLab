package com.company;

public class Army {
    public static void main(String [] args) {
        Warrior warrior = new LegionAdapter(new RomeLegion());
        int id=0;
        warrior.info(warrior,id);
        warrior.returnStrength();
        id++;
        warrior.info(warrior,id);
        warrior.returnStrength();
    }
}
