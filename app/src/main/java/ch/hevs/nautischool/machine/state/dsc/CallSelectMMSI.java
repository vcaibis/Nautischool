package ch.hevs.nautischool.machine.state.dsc;

import java.util.Timer;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.MachineUtils;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.OffState;

/**
 * Created by Helder on 07.04.2018.
 */

public class CallSelectMMSI implements MachineState {

    MachineContext context;
    int currentIndex = 1;
    Boolean isTwinkled = false;
    Timer timer;

    public CallSelectMMSI(MachineContext context) {init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        //Double de la methodde en dessous, a effacer lorsque les methodes sont finies
    }
    /*@Override
    public void alphanumeric(int sender) {
        MachineData machineData = context.getMachineData();
        if (currentIndex < machineData.currentMMSI.characters.count) {
            machineData.currentMMSI = MachineUtils.replaceSubrange( machineData.currentMMSI, currentIndex, sender.description);
            currentIndex += 1;
            context.setState(this);
            if (currentIndex == machineData.currentMMSI.characters.count) {
                timer!.invalidate();
            }
        }
    }
*/
    @Override
    public void sixteen() {
        //timer!.invalidate()
        context.sixteenButtonPressed();
    }

    @Override
    public void dualwatch() {
      //  timer!.invalidate()
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
        //Double de la methodde en dessous, a effacer lorsque les methodes sont finies
    }
   /* @Override
    public void softkey(int sender, boolean longClick) {
        if (!longClick) {
            MachineData machineData = context.getMachineData();
            if (sender == 1) {
                //timer!.invalidate()
                context.navigateBackToMenuDSCState();
            } else if (sender == 2 && currentIndex > 0) {
                currentIndex -= 1;
                //machineData.currentMMSI = MachineUtils.replaceSubrange( machineData.currentMMSI, currentIndex,  (currentIndex == machineData.currentMMSI.characters.count - 1 ? "-" : "--"));
                if (currentIndex == machineData.currentMMSI.characters.count - 1) {
                   // initializeTimer()
                }
                context.setState(this);
            }
        }
    }
*/
    @Override
    public void cancel() {
       // timer!.invalidate()
        context.setState(new CallState( context));
    }
    @Override
    public void enter() {
        //Double de la methodde en dessous, a effacer lorsque les methodes sont finies
    }
    /*
    @Override
    public void enter() {
        MachineData machineData = context.getMachineData();
        if (currentIndex == machineData.currentMMSI.characters.count) {
            machineData.currentIsMMSI = true;
            context.setState(new CallState(context));
        }
    }
    */

    @Override
    public void distress(boolean touchDown) {
        //timer!.invalidate()
        context.distressButtonPressed();
    }

    @Override
    public void volume(int sender) {
        //context.getMachineData().volume = sender.actualAngle();
        /*if (context.getMachineData().volume == sender.startAngle) {
            //timer!.invalidate()
            context.setState(new OffState( context));
        }
        */
    }

    @Override
    public void squelch(int sender) {
        context.squelchChanged( sender);
    }

    @Override
    public void ptt() {
        //timer!.invalidate()
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
        //    initializeTimer();
        }

        screenLabels.left2 = context.getMachineData().currentMMSI;

        screenLabels.right1 = " ";
        if (currentIndex == 0) {
            screenLabels.right2 = " ";
        } else {
            screenLabels.right2 = "/u{25C0}";
        }
        screenLabels.right3 = " ";
        screenLabels.right4 = " ";
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {
        //timer!.invalidate();
    }
}
