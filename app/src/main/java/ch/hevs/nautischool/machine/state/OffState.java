package ch.hevs.nautischool.machine.state;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.receive.ReceiveState;

/**
 * Created by GCI on 16.03.2018.
 */

public class OffState implements MachineState {
    MachineContext context;

    public OffState(MachineContext context) {
         init(context);
    }

    @Override
    public void alphanumeric(int sender) {

    }

    @Override
    public void sixteen() {

    }

    @Override
    public void dualwatch() {

    }

    @Override
    public void light() {

    }

    @Override
    public void power() {

    }

    @Override
    public void softkey(int sender, boolean longClick) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void enter() {

    }

    @Override
    public void distress(boolean touchDown) {

    }

    @Override
    public void volume(int sender) {
        context.getMachineData().volume = sender;
        context.setState(new ReceiveState(context));
/*
        let machineData = context.machineData

        machineData.volume = sender.actualAngle()
        if machineData.volume != sender.startAngle {
            if machineData.previousSquelch < 0.0 && machineData.squelch < 0.0 {
                context.playSound(soundID: 2)
            }
            context.setState(state: ReceiveState(context: context))
        }
*/


    }

    @Override
    public void squelch(int sender) {

    }

    @Override
    public void ptt() {

    }

    @Override
    public void init(MachineContext context) {
        this.context = context;
    }

    @Override
    public void updateDisplay() {
        ScreenLabels screenLabels = context.getScreenLabels();

        context.initialize();

        screenLabels.left1 = " ";
        screenLabels.left2 = " ";
        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.mid1 = " ";
        screenLabels.mid2 = " ";
        screenLabels.mid3 = " ";
        screenLabels.mid4 = " ";

        screenLabels.right1 = " ";
        screenLabels.right2 = " ";
        screenLabels.right3 = " ";
        screenLabels.right4 = " ";

        screenLabels.bigChan = " ";
        screenLabels.smallChan = " ";

        screenLabels.message1 = " ";
        screenLabels.message2 = " ";
        screenLabels.message3 = " ";

        context.setScreenLabels(screenLabels);
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {

    }
}
