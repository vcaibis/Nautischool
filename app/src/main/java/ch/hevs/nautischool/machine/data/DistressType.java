package ch.hevs.nautischool.machine.data;

/**
 * Created by GCI on 10.03.2018.
 */

/**
 * All the distress type
 */

public class DistressType {

    public final static String UNDEFINED = "Undefined";
    public final static String ABANDONING = "Abandoning";
    public final static String PIRACY = "Piracy";
    public final static String MOB = "M.O.B";
    public final static String FIRE = "Fire";
    public final static String FLOODING = "Flooding";
    public final static String COLLISION = "Collision";
    public final static String GROUNDING = "Grounding";
    public final static String LISTING = "Listing";
    public final static String SINKING = "Sinking";
    public final static String ADRIFT = "Adrift";


    public static String[] getAllValues(){
         return new String[]{UNDEFINED, ABANDONING, PIRACY, MOB, FIRE, FLOODING, COLLISION, GROUNDING, LISTING, SINKING, ADRIFT};
    }
}
