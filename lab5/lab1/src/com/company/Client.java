package com.company;

public class Client {
    public static void main(String [] args) {
        Notifier reportNotifier = new GameCreate(Priority.IMPORTANT);
        Notifier gameNotifier = new WarriorCreate(Priority.ASAP);

        reportNotifier.setNextNotifier(gameNotifier);

        reportNotifier.notifyManager("Starting...", Priority.ROUTINE);
        reportNotifier.notifyManager("Starting new session...", Priority.IMPORTANT);
        reportNotifier.notifyManager("Everything is OK...", Priority.ASAP);
    }
}
