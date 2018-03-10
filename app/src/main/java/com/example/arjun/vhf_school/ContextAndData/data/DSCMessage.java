package com.example.arjun.vhf_school.ContextAndData.data;

/**
 * Created by GCI on 10.03.2018.
 */

// Data contained in a DSC message
public class DSCMessage {
        DSCName name;
        DSCType type;
        String mmsi;
        String channel;
        String dest; //: String?
        DistressDetails alertDetails; //: DistressDetails?
}
