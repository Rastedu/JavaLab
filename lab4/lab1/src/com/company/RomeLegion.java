package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class RomeLegion {
    ArrayList<CreateWarrior> Army = new ArrayList<>();


    void addUnitInfantry(CreateWarrior p){
        Army.add(p);
    }
    void addUnitHorseman(CreateWarrior p){
        Army.add(p);
    }
    void addUnitArcher(CreateWarrior p){
        Army.add(p);
    }



    public int CreateStrength(){
        int s = 0;
        for (int i=0; i<5; ++i) {
            CreateArcher createArcher = new CreateArcher();
            createArcher.makeWarrior(createArcher.id);
            s += createArcher.getStrength();
            addUnitArcher(createArcher);
        }
        for (int i=0; i<5; ++i){
            CreateWarrior createInfantryman = new CreateInfantryman();
            createInfantryman.makeWarrior(createInfantryman.id);
            s += createInfantryman.getStrength();
            addUnitInfantry(createInfantryman);
        }
        for (int i=0; i<5; ++i){
            CreateWarrior createHorseman = new CreateHorseman();
            createHorseman.makeWarrior(createHorseman.id);
            s += createHorseman.getStrength();
            addUnitHorseman(createHorseman);
        }
        System.out.println("The power of legion is:" +s);
        return s;
    }
}
