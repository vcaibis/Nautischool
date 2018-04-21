package ch.hevs.nautischool.machine.state.dsc.menu2;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by Helder on 14.04.2018.
 */

public class MMSIState implements MachineState {
    MachineContext context;

    public MMSIState(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {

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
            if (sender == 1) {
                context.navigateBackToMenuDSCState();
            } else if (sender == 4) {
                context.setState(new MMSIEditGroupState(context));
            }
        }
    }

    @Override
    public void cancel() {
        context.setState(new Menu2State(context));
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

        screenLabels.left1 = "Ships MMSI";
        screenLabels.left2 = machineData.mmsi;
        screenLabels.left3 = "Group MMSI";
        screenLabels.left4 = machineData.groupMMSI;

        screenLabels.mid4 = " ";

        screenLabels.right1 = "DSC";
        screenLabels.right2 = " ";
        screenLabels.right3 = " ";
        screenLabels.right4 = "\u25BA";

        screenLabels.smallChan = " ";
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {

    }
}
