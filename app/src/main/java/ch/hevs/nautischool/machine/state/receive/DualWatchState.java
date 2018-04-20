package ch.hevs.nautischool.machine.state.receive;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.MachineUtils;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.MenuDSCState;

/**
 * Created by Helder on 17.03.2018.
 */

public class DualWatchState implements MachineState {

    MachineContext context;

    public DualWatchState(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        context.getMachineData().selectingChan = ""+sender;
        context.setState(new SelectChanState(context));
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
        if (!longClick && sender == 1) {
            context.setState(new MenuDSCState(context));
        }
    }

    @Override
    public void cancel() {
        context.stopTimer();
        context.setState(new ReceiveState( context));
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
        context.volumeChanged(sender);
    }

    @Override
    public void squelch(int sender) {
        context.squelchChanged(sender);
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

        if (machineData.currentMode != machineData.dualWatchChannel) {
            machineData.currentMode = machineData.dualWatchChannel;
            machineData.dualWatchChannel = machineData.workingChannel;
            context.startTimer(0.5,true);
        }

        screenLabels.left1 = " ";
        screenLabels.left2 = " ";
        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.mid1 = "INT";
        screenLabels.mid2 = MachineUtils.powerLabel(context);
        screenLabels.mid3 = MachineUtils.userLabel(context);
        screenLabels.mid4 = machineData.currentMode;

        screenLabels.right1 = "DSC";
        screenLabels.right2 = " ";
        screenLabels.right3 = " ";
        screenLabels.right4 = " ";

        screenLabels.bigChan = machineData.workingChannel;
        screenLabels.smallChan = " ";

        screenLabels.message1 = " ";
        screenLabels.message2 = " ";
        screenLabels.message3 = " ";
    }

    @Override
    public void updateTimerEnded() {
        ScreenLabels screenLabels = context.getScreenLabels();

        screenLabels.mid2 = MachineUtils.powerLabel( context);
        screenLabels.mid3 = MachineUtils.userLabel( context);
        screenLabels.bigChan = context.getMachineData().workingChannel;
    }

    @Override
    public void didReceiveDSC() {

    }
}
