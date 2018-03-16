package com.example.arjun.vhf_school.ContextAndData;

/**
 * Created by Helder on 02.03.2018.
 */

import com.example.arjun.vhf_school.ContextAndData.state.OffState;

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

    private ScreenLabels screenLabels = new ScreenLabels();
    private MachineData machineData = new MachineData();
    private MachineState currentState = null;
    private Timer timer = new Timer();

    public void init() {
        MachineState offState = new OffState(this);
        setState(offState);
    }
    // change the state ot the context
    public void setState(MachineState state) {
        currentState = state ;
        currentState.updateDisplay();
//        NotificationCenter.default.post(name: MachineContext.screenLabelsChanged, object: nil)
    }
    // Initilize the data when off
    public void initialize() {
       machineData.initialize();
    }

    public ScreenLabels getScreenLabels() {
        return screenLabels;
    }

    public void setScreenLabels(ScreenLabels screenLabels) {
        this.screenLabels = screenLabels;
    }

    public MachineData getMachineData() {
        return machineData;
    }

    public void setMachineData(MachineData machineData) {
        this.machineData = machineData;
    }

    public MachineState getCurrentState() {
        return currentState;
    }


    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
