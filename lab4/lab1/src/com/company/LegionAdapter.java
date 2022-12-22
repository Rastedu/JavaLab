package com.company;

public class LegionAdapter extends RomeLegion implements Warrior{

    private RomeLegion romeLegion;
    LegionAdapter(RomeLegion romeLegion){
        this.romeLegion = romeLegion;
    }

    @Override
    public void info(Warrior warrior,int id) {
       System.out.println("Legion(" +id+")-");
    }

    @Override
    public int returnStrength() {
      return romeLegion.CreateStrength();
    }
}
