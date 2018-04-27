package ch.hevs.nautischool.machine.state.distress;

import java.util.Timer;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.MachineUtils;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by GCI on 21.04.2018.
 */

public class DistressUTC implements MachineState {
    MachineContext context;
    String currentUTC = "--:-- UTC";
    int currentIndex = 0;
    boolean isTwinkled = false;
    Timer timer;

    public DistressUTC(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        if (currentIndex < 5) {
            currentUTC = MachineUtils.replaceSubrange(currentUTC, currentIndex, "" + sender);

            currentIndex += (currentIndex != 1 ? 1 : 2);
            context.setState(this);
            if (currentIndex == 5) {
                timerInvalidate();
            }
        }

    }

    private void timerInvalidate() {
        if(timer == null) return;
        timer.cancel();
        timer.purge();
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
            } else if (sender == 2 && currentIndex > 0) {
                currentIndex -= (currentIndex != 3 ? 1 : 2);
                currentUTC = MachineUtils.replaceSubrange(currentUTC, currentIndex, (currentIndex == 1 ? "-:-" : "--"));
                if (currentIndex == 4) {
                    initializeTimer();
                }
                context.setState(this);
            } else if (sender == 3 && currentIndex == 0) {
                timerInvalidate();
                context.setState(new DistressPosn(context));
            }
        }

    }

    @Override
    public void cancel() {
        timerInvalidate();
        context.setState(new DistressState(context));
    }

    @Override
    public void enter() {
        if (currentIndex == 5) {
            if (isNewUTCValid()) {
                timerInvalidate();
                context.getMachineData().utc = currentUTC;
                context.setState(new DistressState(context));
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
        MachineData machineData = context.getMachineData();

        if (timer == null) {
            initializeTimer();
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
// Nothing to do because no effect
    }

    @Override
    public void didReceiveDSC() {
        timerInvalidate();
    }

    // MARK: - Privates functions

    private void initializeTimer() {
//        timer = Timer.scheduledTimer(withTimeInterval: 0.25, repeats: true, block: { (Timer) in
//            this.currentUTC = MachineUtils.replaceSubrange(this.currentUTC, this.currentIndex, (this.isTwinkled ? " " : "-"));
//            this.isTwinkled = !this.isTwinkled;
//            this.context.setState(this);
//        })
    }

    private boolean isNewUTCValid() {
        int hours = Integer.parseInt(currentUTC.substring(0, 2));
        int minutes = Integer.parseInt(currentUTC.substring(3, 5));
        if (hours > 24) return false;
        if (hours < 0) return false;
        if (minutes > 60) return false;
        return minutes >= 0;
        //       int hours = Int(currentUTC.substring(to: currentUTC.index(currentUTC.startIndex, offsetBy: 2)))
        //       int minutes = Int(currentUTC.substring(with: currentUTC.index(currentUTC.startIndex, offsetBy: 3)..<currentUTC.index(currentUTC.startIndex, offsetBy: 5)))
        //       return hours! < 24 && minutes! < 60;

    }

}
