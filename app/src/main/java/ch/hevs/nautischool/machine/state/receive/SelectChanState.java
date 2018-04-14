package ch.hevs.nautischool.machine.state.receive;

import android.content.Context;

import java.util.Arrays;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.MenuDSCState;

public class SelectChanState implements MachineState{
    MachineContext context;

    public SelectChanState(MachineContext context) {
        init(context);
    }

    @Override
    public void init(MachineContext context) {
        this.context = context;
    }

    @Override
    public void updateDisplay() {
        ScreenLabels screenLabels = context.getScreenLabels();
        MachineData machineData = context.getMachineData();
        machineData.currentMode = MachineData.MACHINEMODE_RECEIVE;

        screenLabels.right1 = " ";
        screenLabels.right2 = " ";
        screenLabels.right3 = " ";
        screenLabels.right4 = " ";

        screenLabels.bigChan = context.getMachineData().selectingChan;
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {
        // Nothing to do because no effect
    }

    @Override
    public void alphanumeric(int sender) {
        MachineData machineData = context.getMachineData();


        context.setState(this);
    }

    @Override
    public void sixteen() {
        context.getMachineData().workingChannel = userChannelOrSixteen(context.getMachineData());
        context.getMachineData().isHighPower = true;
        context.setState(this);
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
        context.setState(this);
    }

    @Override
    public void softkey(int sender, boolean longClick) {

        MachineData machineData = context.getMachineData();
        int index = Arrays.asList(machineData.channels).indexOf(machineData.workingChannel);
        if (longClick) {
            switch (sender) {
                case 2:
                    machineData.userChannel = machineData.workingChannel;
                    context.getScreenLabels().mid2 = "User";
                    context.getScreenLabels().mid3 = "Sel";
                    break;
                case 3:

                    context.getScreenLabels().mid2 = "M/S";
                    if (!machineData.memoryScanChannels[index]) {
                        context.getScreenLabels().mid3 = "Del";
                        machineData.memoryScanNumber -= 1;
                    } else {
                        context.getScreenLabels().mid3 = "Sel";
                        machineData.memoryScanNumber += 1;
                    }

                    if (!machineData.memoryScanChannels[index]) {
                        //machineData.memoryScanChannels[index!] = !machineData.memoryScanChannels[index!];
                        index = Arrays.asList(machineData.channels).indexOf(machineData.workingChannel);
                    }
                    break;
                default:

                    context.getScreenLabels().mid2 = "Scan";
                    if (!machineData.scanChannels[index]) {
                        context.getScreenLabels().mid3 = "Inh";
                        machineData.scanNumber -= 1;
                    } else {
                        context.getScreenLabels().mid3 = "Ena";
                        machineData.scanNumber += 1;
                    }
                    if (!machineData.memoryScanChannels[index]) {
                        //machineData.memoryScanChannels[index!] = !machineData.memoryScanChannels[index!];
                        index = Arrays.asList(machineData.channels).indexOf(machineData.workingChannel);
                    }
                    break;
            }

            context.notifyControllerChannelLabelsChanged();
            context.startTimer(2.0, false);

        } else {
            switch (sender) {
                case 1:
                    context.setState(new MenuDSCState(context));
                    break;
                case 2:
                    if (machineData.workingChannel != "16" && machineData.userChannel != machineData.workingChannel && machineData.userChannel != "16") {
                        context.setState(new TriWatchState(context));
                    }
                    break;
                case 3:
                    if (machineData.memoryScanNumber > 1) {
                        context.setState(new MemoryScanState(context));
                    }
                    break;
                default:
                    if (machineData.scanNumber > 1) {
                        context.setState(new ScanState(context));
                    }
            }
        }
    }

    @Override
    public void cancel() {
            context.setState(new ReceiveState(context));
    }

    @Override
    public void enter() {
        MachineData machineData = context.getMachineData();

        if( Arrays.asList(machineData.channels).contains(machineData.selectingChan)){
            context.getMachineData().workingChannel = context.getMachineData().selectingChan;
        }
        context.setState(new ReceiveState(context));
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
        context.volumeChanged(sender);
    }

    @Override
    public void ptt() {
        context.pttPressed();
    }

    private String userChannelOrSixteen(MachineData machineData) {
        if (machineData.workingChannel == "16" && machineData.userChannel != "16") {
            return machineData.userChannel;
        } else {
            return "16";
        }
    }
}
