package ch.hevs.nautischool.machine.state.receive;

import java.util.Arrays;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.MachineUtils;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.MenuDSCState;

/**
 * Created by Helder on 07.04.2018.
 */

public class ScanState implements MachineState {
    MachineContext context;

    public ScanState(MachineContext context) {
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

    }

    @Override
    public void power() {

    }

    @Override
    public void softkey(int sender, boolean longClick) {
        if (!longClick) {
            MachineData machineData = context.getMachineData();

            if (sender == 1) {
                context.setState(new MenuDSCState(context));
            } else if (sender == 3) {
                if (context.getTimer() != null) {
                    int index = Arrays.asList(machineData.channels).indexOf(machineData.workingChannel);
                   // machineData.scanChannels[index!] = false;
                    machineData.scanNumber -= 1;
                    context.startTimer(0.5, true);
                }
            } else if (sender == 4) {
                if (context.getTimer() == null) {
                    //context.timer!.fire()
                }
                context.startTimer(0.5, true);
            }
        }
    }

    @Override
    public void cancel() {
        context.stopTimer();
        context.setState(new ReceiveState(context));
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

        if (machineData.currentMode != MachineData.MACHINEMODE_SCAN) {
            machineData.currentMode = MachineData.MACHINEMODE_SCAN;
            machineData.workingChannel = "88";
            machineData.workingChannel = MachineUtils.nextChannelInScan( machineData);
            context.startTimer(0.5, true);
        }

        screenLabels.left1 = " ";
        screenLabels.left2 = " ";

        screenLabels.mid1 = "INT";
        //screenLabels.mid2 = MachineUtils.powerLabel(context);
        //screenLabels.mid3 = MachineUtils.userLabel(context);
        //screenLabels.mid4 = machineData.currentMode.rawValue

        screenLabels.right1 = "DSC";
        screenLabels.right2 = " ";
        screenLabels.right3 = "Ihn";
        screenLabels.right4 = "Adv";

        screenLabels.bigChan = machineData.workingChannel;
        screenLabels.smallChan = " ";
    }

    @Override
    public void updateTimerEnded() {
        ScreenLabels screenLabels = context.getScreenLabels();
        MachineData machineData = context.getMachineData();

        //screenLabels.mid2 = MachineUtils.powerLabel(context);
        //screenLabels.mid3 = MachineUtils.userLabel(context);
        screenLabels.bigChan = machineData.workingChannel;
    }

    @Override
    public void didReceiveDSC() {

    }
}
