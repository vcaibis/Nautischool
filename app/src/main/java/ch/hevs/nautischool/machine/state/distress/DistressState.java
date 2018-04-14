package ch.hevs.nautischool.machine.state.distress;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.receive.ReceiveState;

/**
 * Created by GCI on 17.03.2018.
 */

public class DistressState implements MachineState {
    MachineContext context;

    public DistressState(MachineContext context) {
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
            if (sender == 2) {
                context.setState(new DistressPosn( context));
            } else if (sender == 3) {
             //   context.setState(new DistressUTC( context));
            } else if (sender == 4) {
                context.getMachineData().currentDistressType = (context.getMachineData().currentDistressType + 1) % context.getMachineData().distressType.length;
                context.setState(this);
            }
        }
    }

    @Override
    public void cancel() {
        context.setState(new ReceiveState(context));
    }

    @Override
    public void enter() {

    }

    @Override
    public void distress(boolean touchDown) {
        if (touchDown) {
 //           context.setState(new DistressSend( context));
        }
    }

    @Override
    public void volume(int sender) {
        context.volumeChanged( sender);
    }

    @Override
    public void squelch(int sender) {
        context.squelchChanged( sender);
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

        screenLabels.left1 = "Distress Alert";
        screenLabels.left2 = machineData.longitude + " " + machineData.latitude;
        screenLabels.left3 = machineData.utc;
        screenLabels.left4 = machineData.distressType[machineData.currentDistressType];

        screenLabels.mid1 = " ";
        screenLabels.mid2 = " ";
        screenLabels.mid3 = " ";
        screenLabels.mid4 = " ";

        screenLabels.right1 = " ";
        screenLabels.right2 = "/u{25BA}";
        screenLabels.right3 = "/u{25BA}";
        screenLabels.right4 = "/u{25BA}";

        screenLabels.bigChan = " ";
        screenLabels.smallChan = " ";

        screenLabels.message1 = " ";
        screenLabels.message2 = " ";
        screenLabels.message3 = " ";
    }

    @Override
    public void updateTimerEnded() {
        context.getScreenLabels().smallChan = context.getMachineData().workingChannel;
    }

    @Override
    public void didReceiveDSC() {

    }
}
