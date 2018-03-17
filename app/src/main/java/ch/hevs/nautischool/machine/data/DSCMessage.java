package ch.hevs.nautischool.machine.data;

/**
 * Created by GCI on 10.03.2018.
 */

// Data contained in a DSC message
public class DSCMessage {
    private String name;
    private String type;
    private String mmsi;
    private String channel;
    private String dest; //: String?
    private DistressDetails alertDetails; //: DistressDetails?

    public DSCMessage(String name, String type, String mmsi, String channel, String dest, DistressDetails alertDetails) {
        this.name = name;
        this.type = type;
        this.mmsi = mmsi;
        this.channel = channel;
        this.dest = dest;
        this.alertDetails = alertDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public DistressDetails getAlertDetails() {
        return alertDetails;
    }

    public void setAlertDetails(DistressDetails alertDetails) {
        this.alertDetails = alertDetails;
    }
}
