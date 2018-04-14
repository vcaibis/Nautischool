package ch.hevs.nautischool.machine;

/**
 * Created by Helder on 02.03.2018.
 */

import ch.hevs.nautischool.machine.data.Contact;
import ch.hevs.nautischool.machine.data.DSCMessage;
import ch.hevs.nautischool.machine.data.DSCName;
import ch.hevs.nautischool.machine.data.DSCType;
import ch.hevs.nautischool.machine.data.DistressType;

import java.util.ArrayList;
import java.util.List;

/**
 * NOT STATIC GLOBAL variables, méthodes
 */
public class MachineData {
    // Mode of the machines that run in background
    public final static String MACHINEMODE_RECEIVE = "Rx";
    public final static String MACHINEMODE_DUALWATCH = "D/W";
    public final static String MACHINEMODE_TRIWATCH = "T/W";
    public final static String MACHINEMODE_MEMORYSCAN = "M/S";
    public final static String MACHINEMODE_SCAN = "Scan";
    public final static String MACHINEMODE_TRANSMIT = "Tx";

    public String channels[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28",
                    "61", "62", "63", "64", "65", "66", "67", "68", "69",
                    "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
                    "81", "82", "83", "84", "85", "86", "87", "88"};
    public String contrasts[] = {"MIN", "-7", "-6", "-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3", "4", "5", "6", "MAX"};
    public String [] distressType = DistressType.getAllValues();
    public String mDSCType[][] = {
                    {"Routine to", ""},
                    {"All ships", "Safety"},
                    {"All ships", "Urgency"}
            };

    // All states
    public int volume;
    public int squelch;
    public int previousSquelch;
    public String workingChannel = "16";
    public String userChannel = "16";
    public boolean isHighPower = true;
    public String currentMode = MACHINEMODE_RECEIVE;
    public boolean isBeepOn = true;
    public int contrast;   // index du tableau de contrasts
    public boolean screenColor = true;

    // Dualwatch state
    public String dualWatchChannel = "16";

    // Triwatch state
    public String triWatchChannel = "16";
    public String selectingChan = "16";

    // Memory scan state
    public boolean memoryScanChannels[] = new boolean[channels.length];    // same length of channel array
    public int memoryScanNumber = 0;

    // Scan state
    public boolean scanChannels[] = new boolean[channels.length];    // same length of channel array
    public int scanNumber;

    // MMSI state
    public String mmsi = "278111222";
    public String groupMMSI = "027811100";

    // Position
    public String latitude = "No position";
    public String longitude = "Available";
    public String utc = "88:88 UTC";


    public List<DSCMessage> alertLogs;
    //Pas plutot un Array de Contacts
    //Contact contacts[]= {Contact(name: "MrcKoper", mmsi: "002780200"), Contact(name: "Splitradio", mmsi: "002380100"), Contact(name: "Spinaker", mmsi: "027811100")}
    public List<Contact> contacts;
    public int currentContact = 0;

    public int currentType = 0;
    public String currentChannel = "16";

    public String currentMMSI = "---------";
    public boolean currentIsMMSI = false;
    public int currentDistressType = 0;

    public DSCMessage receivedDSC;
    public DSCMessage toSendDSC;

    // Initialize the array and data
    public MachineData() {
        initialize();
//        DSCType.append(("Group Call", "\(self.groupMMSI)"));
        populateLogs();
        populateContacts();
    }

    // Add some fictive contacts
    private void populateContacts() {
        contacts = new ArrayList<>();
        contacts.add(new Contact("MrcKoper", "002780200"));
        contacts.add(new Contact("Splitradio", "002380100"));
        contacts.add(new Contact("Spinaker", "027811100"));

    }

    // Add some fictive logs
        private void populateLogs() {
            alertLogs = new ArrayList<>();
            alertLogs.add(new DSCMessage(DSCName.INDIVIDUAL,DSCType.ROUTINE,"012345678","08",mmsi,null));
            alertLogs.add(new DSCMessage(DSCName.SHIPS,DSCType.SAFETY,"334455667","16",mmsi,null));
            alertLogs.add(new DSCMessage(DSCName.GROUPCALL,DSCType.ROUTINE,"012345678","08",groupMMSI,null));
            alertLogs.add(new DSCMessage(DSCName.DISTRESS,DSCType.ALERT,"929394959","16",null,null));

//            alertLogs.append(DSCMessage(name: .distress, type: .alert, mmsi:"929394959", channel:
//            "16", dest:nil, alertDetails:DistressDetails(latitude:"020°16N", longitude:
//            "16°84", utc:"08:12 UTC", mmsi:"929394959", type: .abandoning)))
        }

            // Initialize the call parameters
            public void initCallParam() {
                currentType = 0;
                currentChannel = "16";
                currentContact = 0;
            }


            // Initialize the data when off
            public void initialize() {
                volume = 0;
                contrast = contrasts.length - 1;
                userChannel = "16";
                workingChannel = "16";
                isHighPower = true;

                // memoryScanChannels : all to false
                for(int i = 0; i <= memoryScanChannels.length-1; i++)
                    memoryScanChannels[i] = false;
                memoryScanNumber = 0;

                // scanChannels : all to true
                for(int i = 0; i <= scanChannels.length-1; i++)
                    scanChannels[i] = true;
                scanNumber = channels.length;

                currentMode = MACHINEMODE_RECEIVE ;
                isBeepOn = true;
            }


}

