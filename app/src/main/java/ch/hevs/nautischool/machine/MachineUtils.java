package ch.hevs.nautischool.machine;

/**
 * Created by Helder on 02.03.2018.
 */
//TODO: Helder

import java.util.Arrays;

/**
 * STATIC GLOBAL variables, méthodes
 */
public class MachineUtils {

    public static String highLetters[][] = {
            {"0"},
            {" ", "1"},
            {"A","B","C","2"},
            {"D","E","F","3"},
            {"G","H","I","4"},
            {"J","K","L","5"},
            {"M","N","O","6"},
            {"P","Q","R","S","7"},
            {"T","U","V","8"},
            {"W","X","Y","Z","9"}};

    public static String lowLetters[][] ={
            {"0"},
            {" ", "1"},
            {"a","b","c","2"},
            {"d","e","f","3"},
            {"g","h","i","4"},
            {"j","k","l","5"},
            {"m","n","o","6"},
            {"p","q","r","s","7"},
            {"t","u","v","8"},
            {"w","x","y","z","9"}
    };

    public static String channels[] ={
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "61", "62",
            "63", "64", "65", "66", "67", "68", "69", "71", "72", "73",
            "74", "75", "76", "77", "78", "79", "80", "81", "82", "83",
            "84", "85", "86", "87", "88"
    };

    public static String contrasts[] ={
            "MIN", "-7", "-6", "-5", "-4", "-3", "-2", "-1", "0", "1",
            "2", "3", "4", "5", "6", "MAX"
    };



    //Detect if the label is High or low power
    public static String powerLabel(MachineContext context ){
        MachineData machineData = context.getMachineData();

        if (machineData.isHighPower || machineData.workingChannel == "15"
                || machineData.workingChannel == "17") {
            return "Lo";
        } else {
            return "Hi";
        }
    }



    // Detect if the power value is high or low
    public static boolean powerValue(MachineContext context ){
        MachineData machineData = context.getMachineData();
        return !machineData.isHighPower &&
                machineData.workingChannel != "15" &&
                machineData.workingChannel != "17";
    }

    // Detect if the working channel is the user channel
    public static String userLabel(MachineContext context ){
        MachineData machineData = context.getMachineData();

        if (machineData.userChannel == machineData.workingChannel) {
            return "User";
        } else {
            return " ";
        }
    }


    // Change the channel when dual watch is activated
    public static String switchChannel(MachineData machineData ){
        if (machineData.workingChannel == machineData.dualWatchChannel) {
            return "16";
        } else {
            return machineData.dualWatchChannel;
        }
    }

    // Find the next channel in the memory scan
    public static String nextChannelInMemoryScan(MachineData machineData ){
        int index = Arrays.asList(machineData.channels).indexOf(machineData.workingChannel);
        //Arrays.asList(machineData.channels).indexOf(machineData.workingChannel)
        for (int i = 1; i < machineData.channels.length; i++) {
            for (int j = (i + index); j < machineData.channels.length; j++) {
                if (machineData.memoryScanChannels[j]) {
                    return machineData.channels[j];
                }
            }
        }
        return ""; // Should never happen
    }

    // Find the next channel in the scan
    public static String nextChannelInScan(MachineData machineData ){
        int index = Arrays.asList(machineData.channels).indexOf(machineData.workingChannel);
        for (int i = 1; i < machineData.channels.length; i++) {
            for (int j = (i + index); j < machineData.channels.length; j++) {
                if (machineData.scanChannels[j]) {
                    return machineData.channels[j];
                }
            }
        }
        return ""; // Should never happen
    }

    // Change the working channel when triwatch is activated
    public static String triwatchSwitchState(MachineData machineData ){
        if (machineData.workingChannel == "16") {
            return machineData.userChannel;
        } else if (machineData.workingChannel == machineData.userChannel) {
            return machineData.triWatchChannel;
        } else {
            return "16";
        }
    }
    // Replace a subrange of characters in a given string
    public static String replaceSubrange(String of, int at,String  with){
        return of.substring(0,at) + with + of.substring(at+with.length());
//        return of.replacingCharacters(in: of.index(of.startIndex, offsetBy: at)..<of.index(of.startIndex, offsetBy: at + with.characters.count), with: with)
    }


}
