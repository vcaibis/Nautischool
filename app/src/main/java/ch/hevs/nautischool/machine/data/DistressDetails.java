package ch.hevs.nautischool.machine.data;

/**
 * Created by GCI on 10.03.2018.
 */

// Data contained in the details of a distress message
public class DistressDetails {
    public String latitude;
    public String longitude;
    public String utc;
    public String mmsi;
    public String distressType;

    public DistressDetails(String latitude, String longitude, String utc, String mmsi, String distressType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.utc = utc;
        this.mmsi = mmsi;
        this.distressType = distressType;
    }

    public DistressDetails(){

    }
}
