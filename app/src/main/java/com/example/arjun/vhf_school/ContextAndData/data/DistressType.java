package com.example.arjun.vhf_school.ContextAndData.data;

/**
 * Created by GCI on 10.03.2018.
 */

public class DistressType {
    // All the distress type
        final static String UNDEFINED = "Undefined";
        final static String ABANDONING = "Abandoning";
        final static String PIRACY = "Piracy";
        final static String MOB = "M.O.B";
        final static String FIRE = "Fire";
        final static String FLOODING = "Flooding";
        final static String COLLISION = "Collision";
        final static String GROUNDING = "Grounding";
        final static String LISTING = "Listing";
        final static String SINKING = "Sinking";
        final static String ADRIFT = "Adrift";


    static String[] getAllValues(){
         return new String[]{UNDEFINED, ABANDONING, PIRACY, MOB, FIRE, FLOODING, COLLISION, GROUNDING, LISTING, SINKING, ADRIFT};
    }
}
