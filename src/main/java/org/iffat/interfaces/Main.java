package org.iffat.interfaces;

public class Main {
    public static void main(String[] args) {
        Bird bird = new Bird();
        Animal animal = bird;
        FlightEnabled flier = bird;
        Trackable tracked = bird;

        bird.move();
        animal.move();

        flier.takeOff();
        flier.fly();
        flier.land();

        tracked.track();

        // can't
        //flier.move();
        //tracked.move();
    }
}
