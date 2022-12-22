package com.company;

public aspect WarAspect {
    pointcut infoMethod(): call( * Warrior.info(..));
    pointcut getStrengthMethod(): call(* Warrior.returnStrength());

    pointcut ArcherInfo(Warrior warrior,int id): call(* CreateArcher.makeWarrior(..)) && args(warrior, id);
    pointcut ArcherGetStrength(): call(* CreateArcher.getStrength(..));
    void around(Warrior warrior, int id): ArcherInfo(warrior, id){
        new Archer().info(warrior, id);
    }
     int around(): ArcherGetStrength(){
       return  new Archer().returnStrength();
    }
    pointcut HorsemanInfo(Warrior warrior,int id): call(* CreateHorseman.makeWarrior(..)) && args(warrior, id);
    pointcut HorsemanGetStrength(): call(* CreateHorseman.getStrength(..));
    void around(Warrior warrior, int id):HorsemanInfo(warrior, id){
         new Horseman().info(warrior, id);
    }
    void around(): HorsemanGetStrength(){
         new Horseman().returnStrength();
    }
    pointcut InfantrymanInfo(Warrior warrior,int id): call(* CreateInfantryman.makeWarrior(..)) && args(warrior, id);
    pointcut InfantrymanGetStrength(): call(* CreateInfantryman.getStrength(..));
    void around(Warrior warrior, int id):InfantrymanInfo(warrior, id){
         new Infantryman().info(warrior, id);
    }
    void around(): InfantrymanGetStrength(){
         new Infantryman().returnStrength();
    }
    //after() returning(String object1):GetName1(){name = name + object1;}

    //pointcut saySecName(String person): call(* HelloWorld.sayToPerson(String,String)) && args(*,person); //&& call(* HelloWorld.setFamilyName(..)) && args(FamilyName);
    //void around(String person): saySecName(person){
       // proceed(person + " " + name );
    //}
}
