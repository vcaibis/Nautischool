package ch.hevs.nautischool.machine.state.dsc.menu2;

import java.util.Timer;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.MachineUtils;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by GCI on 21.04.2018.
 */

public class MMSIEditGroupState implements MachineState {
    MachineContext context;
    String currentGroupMMSI = "0--------";
    int currentIndex = 1;
    boolean isTwinkled = false;
    Timer timer;


    public MMSIEditGroupState(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        if (currentIndex < currentGroupMMSI.length()) {
            currentGroupMMSI = MachineUtils.replaceSubrange(currentGroupMMSI, currentIndex, ""+sender);
            currentIndex += 1;
            context.setState(this);
            if (currentIndex == currentGroupMMSI.length()) {
                timerInvalidate();
            }
        }
    }

    private void timerInvalidate() {
    }

    @Override
    public void sixteen() {
        timerInvalidate();
        context.sixteenButtonPressed();
    }

    @Override
    public void dualwatch() {
        timerInvalidate();
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

    @Override
    public void softkey(int sender, boolean longClick) {
        if (!longClick) {
            if (sender == 1) {
                timerInvalidate();
                context.navigateBackToMenuDSCState();
            } else if (sender == 2 && currentIndex > 1) {
                currentIndex -= 1;
                currentGroupMMSI = MachineUtils.replaceSubrange(currentGroupMMSI, currentIndex, (currentIndex == currentGroupMMSI.length() - 1 ? "-" : "--"));
                if (currentIndex == currentGroupMMSI.length() - 1) {
                    initializeTimer();
                }
                context.setState(this);
            }
        }
    }

    private void initializeTimer() {
    }

    @Override
    public void cancel() {
        timerInvalidate();
        context.setState(new MMSIState(context));
    }

    @Override
    public void enter() {
        if (currentIndex == currentGroupMMSI.length()) {
            context.getMachineData().groupMMSI = currentGroupMMSI;
            context.setState(new MMSIState(context));
        }
    }

    @Override
    public void distress(boolean touchDown) {
        timerInvalidate();
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
        timerInvalidate();
        context.pttPressed();
    }

    @Override
    public void init(MachineContext context) {
        this.context = context;
    }

    @Override
    public void updateDisplay() {

        ScreenLabels screenLabels = context.getScreenLabels();

        if (timer == null) {
            initializeTimer();
        }

        screenLabels.left1 = "Enter Group MMSI";
        screenLabels.left2 = currentGroupMMSI;
        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.mid4 = " ";

        screenLabels.right1 = " ";
        if (currentIndex <= 1) {
            screenLabels.right2 = " ";
        } else {
            screenLabels.right2 = "\u25C0";
        }
        screenLabels.right3 = " ";
        screenLabels.right4 = " ";

        screenLabels.smallChan = " ";
    }

    @Override
    public void updateTimerEnded() {
// Nothing to do because no effect
    }

    @Override
    public void didReceiveDSC() {
        timerInvalidate();
    }
}
