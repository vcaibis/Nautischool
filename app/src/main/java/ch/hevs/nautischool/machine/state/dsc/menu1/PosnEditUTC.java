package ch.hevs.nautischool.machine.state.dsc.menu1;

import android.content.Context;

import java.util.Timer;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.MachineUtils;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.data.Contact;
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
           // currentUTC = MachineUtils.replaceSubrange(of: currentUTC, at: currentIndex, with: sender.description)
            currentIndex += (currentIndex != 1 ? 1 : 2);
            context.setState(this);
            if (currentIndex == 5) {
         //       timer!.invalidate();
            }
        }
    }

    @Override
    public void sixteen() {
     //   timer!.invalidate();
        context.sixteenButtonPressed();
    }

    @Override
    public void dualwatch() {
     //   timer!.invalidate();
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
            //    timer!.invalidate();
                context.navigateBackToMenuDSCState();
            } else if (sender == 2 && currentIndex > 0) {
                currentIndex -= (currentIndex != 3 ? 1 : 2);
               // currentUTC = MachineUtils.replaceSubrange(of: currentUTC, at: currentIndex, with: (currentIndex == 1 ? "-:-" : "--"));
                if (currentIndex == 4) {
        //            initializeTimer();
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
     //   timer!.invalidate();
        context.setState(new PosnState(context));
    }

    @Override
    public void enter() {
        if (currentIndex == 5) {
            if (isNewUTCValid()) {
        //        timer!.invalidate();
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
     //   timer!.invalidate();
        context.distressButtonPressed();
    }

    @Override
    public void volume(int sender) {
        context.getMachineData().volume = sender;
        if (context.getMachineData().volume == 0) {
         //   timer!.invalidate();
            context.setState(new OffState(context));
        }
    }

    @Override
    public void squelch(int sender) {
        context.squelchChanged(sender);
    }

    @Override
    public void ptt() {
     //   timer!.invalidate();
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
        //    initializeTimer();
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
    //    timer!.invalidate()
    }

    private void initializeTimer(){
     /*   timer = Timer.scheduledTimer(withTimeInterval: 0.25, repeats: true, block: { (Timer) in
            this.currentUTC = MachineUtils.replaceSubrange(this.currentUTC,  this.currentIndex,  (this.isTwinkled ? " " : "-"));
            this.isTwinkled = !this.isTwinkled;
            this.context.setState(this);
       });
       */
}

    private Boolean isNewUTCValid(){
    //    int hours = Int(currentUTC.substring(to: currentUTC.index(currentUTC.startIndex, offsetBy: 2)));
     //   int minutes = Int(currentUTC.substring( currentUTC.index(currentUTC.startIndex, offsetBy: 3)..<currentUTC.index(currentUTC.startIndex, offsetBy: 5)));
     //   return hours !< 24 && minutes !< 60;
        return true;
    }

}
