package ch.hevs.nautischool.machine.state.dsc.menu1;

import javax.crypto.Mac;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by Helder on 14.04.2018.
 */

public class LCDState implements MachineState {
    MachineContext context;

    public LCDState(MachineContext context) {
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
        MachineData machineData = context.getMachineData();
        if (!longClick) {
            if (sender == 1) {
                context.navigateBackToMenuDSCState();
            } else if (sender == 3) {
                if (machineData.contrast != machineData.contrasts.length - 1) {
                    machineData.contrast += 1;
                    context.setState(this);
                }
            } else if (sender == 4) {
                if (machineData.contrast >= 1) {
                    machineData.contrast -= 1;
                    context.setState(this);
                }
            }
        }
    }

    @Override
    public void cancel() {
        context.setState(new Menu1State(context));
    }

    @Override
    public void enter() {}

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

        screenLabels.left1 = "Contrast";
        screenLabels.left2 = machineData.contrasts[machineData.contrast];

        screenLabels.right2 = " ";
        screenLabels.right3 = "\u25B2"   ; // Black up-pointing triangle
        screenLabels.right4 = "\u25BC"    ;// Black down-pointing triangle
    }

    @Override
    public void updateTimerEnded() {
        context.getScreenLabels().smallChan = context.getMachineData().workingChannel;
    }

    @Override
    public void didReceiveDSC() {

    }
}
