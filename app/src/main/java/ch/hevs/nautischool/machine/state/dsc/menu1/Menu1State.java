package ch.hevs.nautischool.machine.state.dsc.menu1;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.menu2.Menu2State;

/**
 * Created by GCI on 17.03.2018.
 */

public class Menu1State implements MachineState {
    MachineContext context;

    public Menu1State(MachineContext context) {
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
            switch (sender) {
                case 1:
                    context.navigateBackToMenuDSCState();
                    break;
                case 2:
                    context.setState(new LCDState(context));
                    break;
                case 3 :
                    context.setState(new PosnState(context));
                    break;
                default:
                    context.setState(new Menu2State(context));
                    break;
            }
        }
    }

    @Override
    public void cancel() {
        context.navigateBackToMenuDSCState();
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

        screenLabels.left3 = " ";

        screenLabels.mid4 = context.getMachineData().currentMode;

        screenLabels.right1 = "DSC";
        screenLabels.right2 = "LCD";
        screenLabels.right3 = "Posn";
        screenLabels.right4 = "More";

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
