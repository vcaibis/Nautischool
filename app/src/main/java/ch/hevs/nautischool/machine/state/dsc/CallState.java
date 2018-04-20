package ch.hevs.nautischool.machine.state.dsc;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.call.CallSelectChanState;
import ch.hevs.nautischool.machine.state.dsc.call.CallSendState;

/**
 * Created by Helder on 07.04.2018.
 */

public class CallState implements MachineState {

    MachineContext context;

    public CallState(MachineContext context) {init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        if (context.getMachineData().currentType == 0) {
            context.getMachineData().currentMMSI = sender + "--------";
            context.setState(new CallSelectMMSI(context));
        }
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

    }

    @Override
    public void softkey(int sender, boolean longClick) {
        if (!longClick) {
            MachineData machineData = context.getMachineData();
            if (sender == 1) {
                machineData.currentType = (machineData.currentType + 1) % machineData.mDSCType.length;
                context.setState(this);
            } else if (sender == 2 && machineData.currentType == 0 && machineData.contacts.size() > 0) {
                machineData.currentContact = (machineData.currentContact + 1) % machineData.contacts.size();
                machineData.currentIsMMSI = false;
                context.setState(this);
            } else if (sender == 3 && machineData.currentType != 2) {
                machineData.selectingChan = machineData.currentChannel;
                context.setState(new CallSelectChanState(context));
            } else if (sender == 4 && !(machineData.currentType == 0 && machineData.contacts.isEmpty() && !machineData.currentIsMMSI)) {
                context.setState(new CallSendState(context));
            }
        }
    }

    @Override
    public void cancel() {
        context.setState(new MenuDSCState(context));
    }

    @Override
    public void enter() {

    }

    @Override
    public void distress(boolean touchDown) {
        context.distressButtonPressed();
    }

    @Override
    public void volume(int sender) {
        context.volumeChanged( sender);
    }

    @Override
    public void squelch(int sender) {
        context.squelchChanged( sender);
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

        screenLabels.left1 = machineData.mDSCType[machineData.currentType][0];
        if (machineData.currentType == 0) {
            if (machineData.currentIsMMSI) {
                screenLabels.left2 = machineData.currentMMSI;
            } else {
                screenLabels.left2 = (machineData.contacts.isEmpty() ? "---------" : machineData.contacts.get(machineData.currentContact).name);
            }
        } else {
            screenLabels.left2 = machineData.mDSCType[machineData.currentType][1];
        }

        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.mid4 = " ";

        screenLabels.right1 = "Type";
        screenLabels.right2 = (machineData.currentType == 0 ? "Dir" : " ");
        screenLabels.right3 = (machineData.currentType == 2 ? "Ch16" : "Ch" + machineData.currentChannel);
        screenLabels.right4 = (machineData.currentType == 0 && machineData.contacts.isEmpty() && !machineData.currentIsMMSI ? " " : "Send");

        screenLabels.smallChan = context.getMachineData().workingChannel;
    }

    @Override
    public void updateTimerEnded() {
        context.getScreenLabels().smallChan = context.getMachineData().workingChannel;
    }

    @Override
    public void didReceiveDSC() {

    }
}
