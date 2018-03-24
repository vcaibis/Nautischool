package ch.hevs.nautischool.machine.state.dsc;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by GCI on 23.03.2018.
 */
public class MenuDSCState  implements MachineState {
    MachineContext context;

    // Constructor
    public MenuDSCState(MachineContext context) {
        init(context);
    }
    @Override
    public void alphanumeric(int sender) {

    }

    @Override
    public void sixteen() {

    }

    @Override
    public void dualwatch() {

    }

    @Override
    public void light() {

    }

    @Override
    public void power() {

    }

    @Override
    public void softkey(int sender, boolean longClick) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void enter() {

    }

    @Override
    public void distress(boolean touchDown) {

    }

    @Override
    public void volume(int sender) {

    }

    @Override
    public void squelch(int sender) {

    }

    @Override
    public void ptt() {

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

        screenLabels.mid1 = " ";
        screenLabels.mid2 = " ";
        screenLabels.mid3 = " ";
        screenLabels.mid4 = machineData.currentMode;

        screenLabels.right1 = "Rad";
        screenLabels.right2 = "Call";
        screenLabels.right3 = "Log";
        screenLabels.right4 = "Menu";

        screenLabels.bigChan = " ";
        screenLabels.smallChan = machineData.workingChannel;

        screenLabels.message1 = " ";
        screenLabels.message2 = " ";
        screenLabels.message3 = " ";

        // set labels of screen
        context.setScreenLabels(screenLabels);
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {

    }
}
