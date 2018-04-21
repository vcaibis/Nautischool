package ch.hevs.nautischool.machine.state.dsc.menu2;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.data.Contact;

/**
 * Created by Helder on 14.04.2018.
 */

public class DirState implements MachineState {

    MachineContext context;
    public DirState(MachineContext context) {
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
            if (sender == 1) {
                context.navigateBackToMenuDSCState();
            } else if (sender == 2 && context.getMachineData().contacts.size() > 0) {
                context.setState(new DirViewState(context));
            } else if (sender == 3 && context.getMachineData().contacts.size() < 16) {
                context.setState(new DirAddState(context));
            }
        }
    }

    @Override
    public void cancel() {
        context.setState(new Menu2State(context));
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
        MachineData machineData = context.getMachineData();

        screenLabels.left1 = "Directory";
        screenLabels.left2 = " ";
        screenLabels.left3 = (machineData.contacts.size() == 0 ? "Empty" : "Used "+machineData.contacts.size()+"/16");
        screenLabels.left4 = " ";

        screenLabels.mid4 = " ";

        screenLabels.right2 = (machineData.contacts.size() == 0 ? " " : "View");
        screenLabels.right3 = (machineData.contacts.size() < 16 ? "Add" : " ");
        screenLabels.right4 = " ";

        screenLabels.smallChan = " ";
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {

    }
}
