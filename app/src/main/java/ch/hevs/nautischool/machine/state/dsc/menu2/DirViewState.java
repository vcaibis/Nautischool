package ch.hevs.nautischool.machine.state.dsc.menu2;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by Helder on 14.04.2018.
 */

public class DirViewState implements MachineState {
    MachineContext context;
    public DirViewState(MachineContext context) {init(context);
    }

    @Override
    public void alphanumeric(int sender) {

    }

    @Override
    public void sixteen() {
        context.getMachineData().currentContact = 0;
        context.sixteenButtonPressed();
    }

    @Override
    public void dualwatch() {
        context.getMachineData().currentContact = 0;
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
            MachineData machineData = context.getMachineData();
            if (sender == 1) {
                context.getMachineData().currentContact = 0;
                context.navigateBackToMenuDSCState();
            } else if (sender == 2 && machineData.currentContact > 0) {
                machineData.currentContact -= 1;
                context.setState(this);
            } else if (sender == 3 && machineData.currentContact < context.getMachineData().contacts.size()-1 ){
                machineData.currentContact += 1;
                context.setState(this);
            } else if (sender == 4) {
                context.setState(new DirEditState(context));
            }
        }
    }

    @Override
    public void cancel() {
        context.getMachineData().currentContact = 0;
        context.setState(new DirState(context));

    }

    @Override
    public void enter() {

    }

    @Override
    public void distress(boolean touchDown) {
        context.getMachineData().currentContact = 0;
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
        context.getMachineData().currentContact = 0;
        context.pttPressed();
    }

    @Override
    public void init(MachineContext context) {this.context=context;

    }

    @Override
    public void updateDisplay() {
        ScreenLabels screenLabels = context.getScreenLabels();
        MachineData machineData = context.getMachineData();

        screenLabels.left2 = (machineData.currentContact < 10 ? "0"+(machineData.currentContact+1) : ""+(machineData.currentContact+1));
        screenLabels.left3 = machineData.contacts.get(machineData.currentContact).name;
        screenLabels.left4 = machineData.contacts.get(machineData.currentContact).mmsi;

        screenLabels.right2 = (machineData.currentContact == 0 ? " " : "Back");
        screenLabels.right3 = (machineData.currentContact == machineData.contacts.size()-1 ? " " : "Next");
        screenLabels.right4 = "Edit";
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {

    }
}
