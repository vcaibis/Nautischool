package com.example.arjun.vhf_school.ContextAndData;

/**
 * Created by Helder on 02.03.2018.
 */

import com.example.arjun.vhf_school.ContextAndData.state.OffState;

import java.util.Timer;

/**
 * Simulateur (context du pattern)
 */
public class MachineContext implements MachineControls{

    // MARK: - Attributes

//    static let screenLabelsChanged = Notification.Name("ScreenLabelsChanged")
//    static let channelLabelsChanged = Notification.Name("ChannelLabelsChanged")
//    static let screenColorChanged = Notification.Name("ScreenColorChanged")
//    static let playSound = Notification.Name("PlaySound")

    private ScreenLabels screenLabels = new ScreenLabels();
    private MachineData machineData = new MachineData();
    private MachineState currentState = null;
    private Timer timer = new Timer();

    public MachineContext() {
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

    @Override
    public void alphanumeric(int sender) {
        currentState.alphanumeric(sender);
    }

    @Override
    public void sixteen() {
        currentState.sixteen();
    }

    @Override
    public void dualwatch() {
        currentState.dualwatch();
    }

    @Override
    public void light() {
        currentState.light();
    }

    @Override
    public void power() {
        currentState.power();
    }

    @Override
    public void softkey(int sender, boolean longClick) {
        currentState.softkey(sender,longClick);
    }

    @Override
    public void cancel() {
        currentState.cancel();
    }

    @Override
    public void enter() {
        currentState.enter();
    }

    @Override
    public void distress(boolean touchDown) {
        currentState.distress(touchDown);
    }

    @Override
    public void volume(int sender) {
        currentState.volume(sender);
    }

    @Override
    public void squelch(int sender) {
        currentState.squelch(sender);
    }

    @Override
    public void ptt() {
        currentState.ptt();
    }
}
