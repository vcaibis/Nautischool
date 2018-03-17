package ch.hevs.nautischool.machine;

/**
 * Created by Helder on 02.03.2018.
 */

import ch.hevs.nautischool.machine.state.receive.DualWatchState;
import ch.hevs.nautischool.machine.state.OffState;
import ch.hevs.nautischool.machine.state.receive.ReceiveState;
import ch.hevs.nautischool.machine.state.distress.DistressState;

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
        initialize();
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

    // Timer to change the current channel (D/W, scan, ms)
    public void startTimer(double seconds, boolean repeats) {
        // to use a timer or a thread ?
    }
    public void stopTimer(){
        // to use a timer or a thread ?
    }
    // Generic method when the sixteen button is pressed
    public void  sixteenButtonPressed() {
        stopTimer();
        machineData.workingChannel = "16";
        machineData.isHighPower = true;
        setState(new ReceiveState(this));
    }

    // Generic method when the d/w button is pressed
    public void dualWatchButtonPressed() {
        if (machineData.currentMode == MachineData.MACHINEMODE_DUALWATCH) {
            stopTimer();
            setState(new ReceiveState(this));
        } else {
            if (machineData.workingChannel != "16") {
                setState(new DualWatchState(this));
            }
        }
    }
    // Generic method when the distress button is pressed
    public void distressButtonPressed() {
        stopTimer();
        setState(new DistressState(this));
    }


        // getter and setter
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
