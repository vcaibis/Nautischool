package ch.hevs.nautischool.machine.state.dsc.menu2;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.menu1.Menu1State;

/**
 * Created by Helder on 17.03.2018.
 */

public class Menu2State implements MachineState {
    MachineContext context;

    public Menu2State(MachineContext context) {
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
            switch (sender) {
                case 1:
                    context.navigateBackToMenuDSCState();
                case 2:
                    context.setState(new MMSIState(context));
                case 3 :
                    context.setState(new DirState(context));
                default:
                    context.setState(new Menu3State(context));
            }
        }
    }

    @Override
    public void cancel() {
        context.setState(new Menu1State(context));
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

        screenLabels.left1 = machineData.longitude;
        screenLabels.left2 = machineData.latitude;
        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.mid4 = machineData.currentMode;

        screenLabels.right1 = "DSC";
        screenLabels.right2 = "MMSI";
        screenLabels.right3 = "Dir";
        screenLabels.right4 = "More";

        screenLabels.smallChan = machineData.workingChannel;
    }

    @Override
    public void updateTimerEnded() {
        context.getScreenLabels().smallChan = context.getMachineData().workingChannel;
    }

    @Override
    public void didReceiveDSC() {

    }
}
