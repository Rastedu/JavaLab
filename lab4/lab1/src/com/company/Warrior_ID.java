package com.company;

public enum Warrior_ID {
    Infantryman_ID, Archer_ID, Horseman_ID;

    Warrior сreateWarrior(Warrior_ID id) {
        Warrior p = null;
        switch (id) {
            case Infantryman_ID:
                p = new Infantryman();
                break;
            case Archer_ID:
                p = new Archer();
                break;
            case Horseman_ID:
                p = new Horseman();
                break;
            default:
                assert (false);
        }
        return p;
    }
}
