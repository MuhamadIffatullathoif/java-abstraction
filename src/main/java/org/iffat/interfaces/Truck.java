package org.iffat.interfaces;

public class Truck implements Trackable{
    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + " is coordinates recorded");
    }
}
