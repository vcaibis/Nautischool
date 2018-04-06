package ch.hevs.nautischool.machine;

/**
 * Created by Helder on 02.03.2018.
 */

import ch.hevs.nautischool.machine.state.receive.DualWatchState;
import ch.hevs.nautischool.machine.state.OffState;
import ch.hevs.nautischool.machine.state.receive.ReceiveState;
import ch.hevs.nautischool.machine.state.distress.DistressState;

import java.util.Timer;

import static ch.hevs.nautischool.machine.MachineData.MACHINEMODE_RECEIVE;

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

    public void notifyControllerChannelLabelsChanged() {
  //      NotificationCenter.default.post(name: MachineContext.channelLabelsChanged, object: nil)
    }
    public void playSound(int soundID) {
 //       let soundDataDict:[String: Int] = ["soundID": soundID];
 //       NotificationCenter.default.post(name: MachineContext.playSound, object: nil, userInfo: soundDataDict)
    }
    // Generic method when the sixteen button is pressed
    public void sixteenButtonPressed() {
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

    // Generic method when the light button is pressed
    public void lightButtonPressed() {
        machineData.screenColor = !machineData.screenColor;
   //     NotificationCenter.default.post(name: MachineContext.screenColorChanged, object: nil)
    }

    // Navigate to the MenuDSCState
    public void navigateBackToMenuDSCState() {
//        setState(new MenuDSCState(this));
    }
    // Generic method when the squelch is changed
    public void squelchChanged(int sender) {
        machineData.previousSquelch = machineData.squelch;
        machineData.squelch = sender;
        if (machineData.squelch < 0.0 && machineData.previousSquelch >= 0.0) {
            playSound(2);
        } else if (machineData.squelch >= 0.0 && machineData.previousSquelch < 0.0) {
            playSound( -2);
        }
    }
    // Generic method when the volume is changed
    public void volumeChanged(int sender) {
        machineData.volume = sender;
        if (machineData.volume == 0 ) {
            stopTimer();
            playSound( -2);
            playSound( -1);
            setState(new OffState(this));
        }
    }

    // Check if change state is needed
    public void  didReceivedAck() {
//        if (currentState instanceof CallWaitAckState) {
//            currentState.didReceiveDSC();
//            machineData.currentMode = MACHINEMODE_RECEIVE;
//            setState(new ReceiveState(this));
//        }
    }
    // Generic method when the ptt is pressed
    public void pttPressed() {
        stopTimer();
//        setState(new TransmitState(this));
    }
    // Change the state to ReceivedDSCState
    public void  didReceivedDSC() {
        currentState.didReceiveDSC();
        stopTimer();
        machineData.currentMode = MACHINEMODE_RECEIVE;
//        setState(new ReceiveDSCState(this));
    }





    // Generic method when the distress button is pressed
    public void distressButtonPressed() {
        stopTimer();
        setState(new DistressState(this));
    }

//-push
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
