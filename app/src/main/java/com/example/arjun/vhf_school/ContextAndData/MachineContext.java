package com.example.arjun.vhf_school.ContextAndData;

/**
 * Created by Helder on 02.03.2018.
 */

import java.util.Timer;

/**
 * Simulateur (context du pattern)
 */
public class MachineContext {

    // MARK: - Attributes

//    static let screenLabelsChanged = Notification.Name("ScreenLabelsChanged")
//    static let channelLabelsChanged = Notification.Name("ChannelLabelsChanged")
//    static let screenColorChanged = Notification.Name("ScreenColorChanged")
//    static let playSound = Notification.Name("PlaySound")

    ScreenLabels screenLabels = new ScreenLabels();
    MachineData machineData = new MachineData();
    MachineState currentState = null;
    Timer timer = new Timer();

    public void init() {
 //       currentState = OffState(this);
    }
    // change the state ot the context
    public void setState(MachineState state) {
        currentState = state ;
//        currentState.updateDisplay():
//        NotificationCenter.default.post(name: MachineContext.screenLabelsChanged, object: nil)
    }
    // Initilize the data when off
    public void initialize() {
//        machineData.initialize();
    }


}
