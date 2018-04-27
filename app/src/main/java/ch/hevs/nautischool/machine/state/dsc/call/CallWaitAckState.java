package ch.hevs.nautischool.machine.state.dsc.call;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.receive.ReceiveState;

import static ch.hevs.nautischool.machine.MachineData.MACHINEMODE_RECEIVE;

/**
 * Created by GCI on 20.04.2018.
 */

public class CallWaitAckState implements MachineState {
    MachineContext context;

    public CallWaitAckState(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        // Nothing to do because no effect
    }

    @Override
    public void sixteen() {
        context.sixteenButtonPressed();
    }

    @Override
    public void dualwatch() {
        context.dualWatchButtonPressed();
    }

    @Override
    public void light() {
        context.lightButtonPressed();
    }

    @Override
    public void power() {
        // Nothing to do because no effect;
    }
    /**
     * Method to Manage the softkeys and change the context state
     * @param sender
     * @param longClick
     */
    @Override
    public void softkey(int sender, boolean longClick) {
        if (!longClick && sender == 4) {
            context.setState(new ReceiveState(context));
        }

    }

    @Override
    public void cancel() {
        context.setState(new ReceiveState(context));
    }

    @Override
    public void enter() {
        // Nothing to do because no effect;
    }

    @Override
    public void distress(boolean touchDown) {
        context.distressButtonPressed();
    }

    @Override
    public void volume(int sender) {
        context.volumeChanged(sender);
    }

    @Override
    public void squelch(int sender) {
        context.volumeChanged(sender);
    }

    @Override
    public void ptt() {
        context.pttPressed();
    }

    @Override
    public void init(MachineContext context) {
        this.context = context;
    }

    @Override
    public void updateDisplay() {
        ScreenLabels screenLabels = context.getScreenLabels();
        MachineData machineData = context.getMachineData();

        machineData.currentMode = MACHINEMODE_RECEIVE;

        screenLabels.left1 = "Waiting for";
        screenLabels.left2 = "acknowledge";

        screenLabels.mid4 = machineData.currentMode;

        screenLabels.right4 = "Stop";

        screenLabels.smallChan = machineData.workingChannel;

        screenLabels.message1 = " ";
        screenLabels.message2 = " ";

    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {

    }
}
