package ch.hevs.nautischool.machine.state.dsc.menu1;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by Helder on 14.04.2018.
 */

public class PosnState implements MachineState {
    MachineContext context;

    public PosnState(MachineContext context) {
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

    /**
     * Method to Manage the softkeys and change the context state
     * @param sender
     * @param longClick
     */
    @Override
    public void softkey(int sender, boolean longClick) {
        if (!longClick) {
            if (sender == 1) {
                context.navigateBackToMenuDSCState();
            } else if (sender == 2) {
                context.setState(new PosnEditPosn(context));
            } else if (sender == 3) {
                context.setState(new PosnEditUTC(context));
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
        screenLabels.left3 = machineData.utc;

        screenLabels.mid4 = " ";

        screenLabels.right2 = "Posn";
        screenLabels.right3 = "UTC";
        screenLabels.right4 = " ";

        context.getScreenLabels().smallChan = " ";
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {

    }
}
