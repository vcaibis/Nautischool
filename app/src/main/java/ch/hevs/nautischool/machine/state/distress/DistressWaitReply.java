package ch.hevs.nautischool.machine.state.distress;

import java.util.Timer;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.receive.ReceiveState;

/**
 * Created by GCI on 21.04.2018.
 */

public class DistressWaitReply implements MachineState {

    MachineContext context;
    Timer timer;

    public DistressWaitReply(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
// Nothing to do because no effect
    }

    @Override
    public void sixteen() {
        timerInvalidate();
        context.sixteenButtonPressed();
    }

    @Override
    public void dualwatch() {
// Nothing to do because no effect
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
// Nothing to do because no effect
    }

    @Override
    public void cancel() {
        timerInvalidate();
        context.setState(new ReceiveState(context));
    }

    @Override
    public void enter() {
// Nothing to do because no effect
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
//            timer = Timer.scheduledTimer(withTimeInterval: 10, repeats: true, block: { (Timer) in
//                context.playSound(3);
//            })
        }

        screenLabels.left1 = "DISTRESS SENT";
        screenLabels.left2 = "AWAITING REPLY";

        screenLabels.mid4 = machineData.currentMode;

        screenLabels.smallChan = machineData.workingChannel;

        screenLabels.message1 = " ";
        screenLabels.message2 = " ";
        screenLabels.message3 = " ";

        context.playSound(3);
    }

    @Override
    public void updateTimerEnded() {
// Nothing to do because no effect
    }

    @Override
    public void didReceiveDSC() {
        timerInvalidate();
    }

    private void timerInvalidate() {
        if(timer==null) return;
        timer.cancel();
        timer.purge();
    }
}
