package ch.hevs.nautischool.machine.state.dsc.call;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.CallState;

/**
 * Created by GCI on 14.04.2018.
 */

public class CallSendState implements MachineState {

    MachineContext context;

    public CallSendState(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        // Nothing to do because no effect
    }

    @Override
    public void sixteen() {
        context.getMachineData().initCallParam();
        context.sixteenButtonPressed();
    }

    @Override
    public void dualwatch() {
        context.getMachineData().initCallParam();
        context.dualWatchButtonPressed();
    }

    @Override
    public void light() {
        context.lightButtonPressed();
    }

    @Override
    public void power() {
// Nothing to do because no effect
    }
    /**
     * Method to Manage the softkeys and change the context state
     * @param sender
     * @param longClick
     */
    @Override
    public void softkey(int sender, boolean longClick) {
        if (!longClick && sender == 1) {
            context.getMachineData().initCallParam();
            context.navigateBackToMenuDSCState();
        }

    }

    @Override
    public void cancel() {
        context.setState(new CallState(context));
    }

    @Override
    public void enter() {
        MachineData machineData = context.getMachineData();
        machineData.workingChannel = machineData.currentChannel;
        context.stopTimer();
        context.setState(new CallSentState(context));
    }

    @Override
    public void distress(boolean touchDown) {
        context.getMachineData().initCallParam();
        context.distressButtonPressed();
    }

    @Override
    public void volume(int sender) {
        context.volumeChanged(sender);
    }

    @Override
    public void squelch(int sender) {
        context.squelchChanged(sender);
    }

    @Override
    public void ptt() {
        context.getMachineData().initCallParam();
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

        screenLabels.left1 = machineData.mDSCType[machineData.currentType][0];
        if (machineData.currentType == 0) {
            screenLabels.left2 = (machineData.currentIsMMSI ? machineData.currentMMSI : machineData.contacts.get(machineData.currentType).name);
        } else {
            screenLabels.left2 = machineData.mDSCType[machineData.currentType][1];
        }

        screenLabels.left3 = "On Ch" + machineData.currentChannel;
        screenLabels.left4 = "Press E to send";

        screenLabels.right1 = " ";
        screenLabels.right2 = " ";
        screenLabels.right3 = " ";
        screenLabels.right4 = " ";

        screenLabels.smallChan = " ";

    }

    @Override
    public void updateTimerEnded() {
        // Nothing to do because no effect
    }

    @Override
    public void didReceiveDSC() {
        // Nothing to do because no effect
    }
}
