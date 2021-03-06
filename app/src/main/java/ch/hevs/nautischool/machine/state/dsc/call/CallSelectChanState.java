package ch.hevs.nautischool.machine.state.dsc.call;

import java.util.Arrays;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.CallState;
import ch.hevs.nautischool.machine.state.receive.ReceiveState;

/**
 * Created by GCI on 14.04.2018.
 */

public class CallSelectChanState implements MachineState {

    MachineContext context;
    String[] defaultChannels = {"06", "08", "72", "77"};
    int currentDefChannel = 3;

    public CallSelectChanState(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        MachineData machineData = context.getMachineData();
        machineData.selectingChan += "00" + machineData.selectingChan + sender;
        machineData.selectingChan = machineData.selectingChan.substring(machineData.selectingChan.length()-2);
        context.setState(this);
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
        if (!longClick) {
            MachineData machineData = context.getMachineData();
            if (sender == 1) {
                machineData.initCallParam();
                context.navigateBackToMenuDSCState();
            } else if (sender == 4) {
                currentDefChannel = (currentDefChannel + 1) % defaultChannels.length;
                machineData.selectingChan = defaultChannels[currentDefChannel];
                context.setState(this);
            }
        }

    }

    @Override
    public void cancel() {
        context.setState(new CallState(context));
    }

    @Override
    public void enter() {
        MachineData machineData = context.getMachineData();

        if( Arrays.asList(machineData.channels).contains(machineData.selectingChan)){
            context.getMachineData().currentChannel = context.getMachineData().selectingChan;
        }
        context.setState(new CallState(context));
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

        screenLabels.left1 = "Reply on channel";
        screenLabels.left2 = machineData.selectingChan;

        screenLabels.right1 = " ";
        screenLabels.right2 = " ";
        screenLabels.right3 = " ";
        screenLabels.right4 = "Chan";

        screenLabels.smallChan = machineData.workingChannel;

    }

    @Override
    public void updateTimerEnded() {
        context.getScreenLabels().smallChan = context.getMachineData().workingChannel;
    }

    @Override
    public void didReceiveDSC() {
        context.getMachineData().initCallParam();
    }
}
