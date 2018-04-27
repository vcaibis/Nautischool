package ch.hevs.nautischool.machine.state.dsc.menu1;

import java.util.Timer;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.MachineUtils;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.OffState;

/**
 * Created by Helder on 14.04.2018.
 */

public class PosnEditUTC implements MachineState {
    MachineContext context;
    String currentUTC = "--:-- UTC";
    int currentIndex = 0;
    Boolean isTwinkled = false;
    Timer timer;

    public PosnEditUTC(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        if (currentIndex < 5) {
            currentUTC = MachineUtils.replaceSubrange(currentUTC, currentIndex, ""+sender);
            currentIndex += (currentIndex != 1 ? 1 : 2);
            context.setState(this);
            if (currentIndex == 5) {
            }
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
            } else if (sender == 2 && currentIndex > 0) {
                currentIndex -= (currentIndex != 3 ? 1 : 2);
                currentUTC = MachineUtils.replaceSubrange(currentUTC, currentIndex, (currentIndex == 1 ? "-:-" : "--"));
                if (currentIndex == 4) {
                }
                context.setState(this);
            } else if (sender == 3 && currentIndex == 0) {
            //    timer!.invalidate();
                context.setState(new PosnEditPosn(context));
            }
        }
    }

    @Override
    public void cancel() {
        context.setState(new PosnState(context));
    }

    @Override
    public void enter() {
        if (currentIndex == 5) {
            if (isNewUTCValid()) {
                context.getMachineData().utc = currentUTC;
                context.setState(new PosnState(context));
            } else {
                currentUTC = "--:-- UTC";
                currentIndex = 0;
                timer = null;
                context.setState(this);
            }
        }
    }

    @Override
    public void distress(boolean touchDown) {
        context.distressButtonPressed();
    }

    @Override
    public void volume(int sender) {
        context.getMachineData().volume = sender;
        if (context.getMachineData().volume == 0) {
            context.setState(new OffState(context));
        }
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

        if (timer == null) {
        }

        screenLabels.left1 = machineData.longitude;
        screenLabels.left2 = machineData.latitude;
        screenLabels.left3 = currentUTC;

        if (currentIndex == 0) {
            screenLabels.right2 = " ";
            screenLabels.right3 = "Posn";
        } else {
            screenLabels.right2 = "\u25C0";
            screenLabels.right3 = " ";
        }
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {
    }

    /**
     * Class to validate the UTC
     * @return boolean
     */
    private boolean isNewUTCValid(){
        int hours = Integer.parseInt(currentUTC.substring(0, 2));
        int minutes = Integer.parseInt(currentUTC.substring(3, 5));
        if (hours > 24) return false;
        if (hours < 0) return false;
        if (minutes > 60) return false;
        return minutes >= 0;

    }

}
