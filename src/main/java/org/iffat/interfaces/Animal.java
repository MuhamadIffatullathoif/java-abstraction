package org.iffat.interfaces;

import java.util.Date;

enum FlightStages implements Trackable {
    GROUNDED,
    LAUNCH,
    CRUISE,
    DATA_COLLECTION;

    @Override
    public void track() {
        if (this != GROUNDED) {
            System.out.println("Monitoring " + this);
        }
    }

    public FlightStages getNextStage() {

        FlightStages[] allStages = values();
        return allStages[(ordinal() + 1) % allStages.length];
    }
}

record DragonFly(String name, String type) implements FlightEnabled {

    @Override
    public void takeOff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void fly() {

    }
}

class Satellite implements OrbitEarth {

    FlightStages stages = FlightStages.GROUNDED;

    @Override
    public void achieveOrbit() {
        transition("Orbit Achieved");
    }

    @Override
    public void takeOff() {
        transition("Taking off");
    }

    @Override
    public void land() {
        transition("Landing");
    }

    @Override
    public void fly() {
        achieveOrbit();
        transition("Data Collection while Orbiting");
    }

    public void transition(String description) {

        System.out.println(description);
        stages = transition(stages);
        stages.track();
    }
}

interface OrbitEarth extends FlightEnabled {

    void achieveOrbit();

    static void log(String description) {
        var today = new Date();
        System.out.println(today + ": " + description);
    }

    private void logStage(FlightStages stages, String description) {

        description = stages + ": " + description;
        log(description);
    }

    @Override
    default FlightStages transition(FlightStages stages) {

        FlightStages nextStage = FlightEnabled.super.transition(stages);
        logStage(stages, "Beginning Transition to " + nextStage);
        return nextStage;
    }
}

interface FlightEnabled {

    double MILES_TO_KM = 1.60934;
    double KM_TO_MILES = 0.621371;

    void takeOff();

    void land();

    void fly();

    default FlightStages transition(FlightStages stages) {
        // System.out.println("transition not implemented on " + this.getClass().getName());
        // return null;
        FlightStages nextStage = stages.getNextStage();
        System.out.println("Transitioning from " + stages + " to " + nextStage);
        return nextStage;
    }
}

interface Trackable {
    void track();
}

public abstract class Animal {

    public abstract void move();
}
