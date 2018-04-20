package ch.hevs.nautischool.machine.state.dsc.menu2;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by Helder on 14.04.2018.
 */

public class Menu3State implements MachineState {
    MachineContext context;

    public Menu3State(MachineContext context) {
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
            } else if (sender == 2) {
                context.getMachineData().isBeepOn = !context.getMachineData().isBeepOn;
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
        this.context=context;
    }

    @Override
    public void updateDisplay() {
        ScreenLabels screenLabels = context.getScreenLabels();

        screenLabels.right1 = "DSC";
        screenLabels.right2 = "Beep";
        screenLabels.right3 = " ";
        screenLabels.right4 = " ";
    }

    @Override
    public void updateTimerEnded() {
        context.getScreenLabels().smallChan = context.getMachineData().workingChannel;
    }

    @Override
    public void didReceiveDSC() {

    }
}
