package ch.hevs.nautischool.machine.data;

/**
 * Created by GCI on 10.03.2018.
 */

// Small structure to hold the name and mmsi of a contact
public class Contact {
    public String name;
    public String mmsi;

    public Contact(String name, String mmsi) {
        this.name = name;
        this.mmsi = mmsi;
    }
}
