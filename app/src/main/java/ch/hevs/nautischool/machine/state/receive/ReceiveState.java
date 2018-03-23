package ch.hevs.nautischool.machine.state.receive;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.MachineUtils;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by Helder on 17.03.2018.
 */

public class ReceiveState implements MachineState {
    MachineContext context;

    public ReceiveState(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
        context.getMachineData().selectingChan = ""+sender;
//        context.setState(new SelectChanState(context));
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
        //context.getMachineData().isHighPower = MachineUtils.powerValue(context);
        //context.setState(this.context);
    }

    @Override
    public void softkey(int sender, boolean longClick) {
        MachineData machineData = context.getMachineData();
/*
        if (longClick) {
            switch (sender) {
                case 2:
                    machineData.userChannel = machineData.workingChannel;
                    context.getScreenLabels().mid2 = "User";
                    context.getScreenLabels().mid3 = "Sel";

                case 3:
                    let index = machineData.channels.index(of: machineData.workingChannel);
                    context.getScreenLabels().mid2 = "M/S";
                    if (machineData.memoryScanChannels[index!]) {
                        context.getScreenLabels().mid3 = "Del";
                        machineData.memoryScanNumber -= 1;
                    }
                    else {
                    context.screenLabels.mid3 = "Sel";
                    machineData.memoryScanNumber += 1;
                }
                machineData.memoryScanChannels[index!] = !machineData.memoryScanChannels[index!];

                default:
                    let index = machineData.channels.index(of: machineData.workingChannel);
                    context.screenLabels.mid2 = "Scan";
                    if (machineData.scanChannels[index!]) {
                    context.screenLabels.mid3 = "Inh";
                    machineData.scanNumber -= 1;
                } else {
                    context.screenLabels.mid3 = "Ena";
                    machineData.scanNumber += 1;
                }
                machineData.scanChannels[index!] = !machineData.scanChannels[index!];
            }

            context.notifyControllerChannelLabelsChanged();
            context.startTimer(seconds: 2.0, repeats: false);

        } else {
            switch (sender) {
                case 1:
                    context.setState(state: MenuDSCState(context));

                case 2:
                    if (machineData.workingChannel != "16" && machineData.userChannel != machineData.workingChannel && machineData.userChannel != "16") {
                    context.setState(state: TriWatchState(context);
                }

                case 3:
                    if (machineData.memoryScanNumber > 1) {
                    context.setState(state: MemoryScanState(context));
                }

                default:
                    if (machineData.scanNumber > 1) {
                    context.setState(state: ScanState(context));
                }
            }
        }*/
    }

    @Override
    public void cancel() {

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
        context.volumeChanged(sender);
    }

    @Override
    public void ptt() {
     //   context.pttPressed();
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

        screenLabels.left1 = " ";
        screenLabels.left2 = " ";
        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.mid1 = "INT";
        //screenLabels.mid2 = MachineUtils.powerLabel(context);
        //screenLabels.mid3 = MachineUtils.userLabel(context);
        //screenLabels.mid4 = machineData.currentMode.rawValue;

        screenLabels.right1 = "DSC";
        screenLabels.right2 = "T/W";
        screenLabels.right3 = "M/S";
        screenLabels.right4 = "Scan";

        screenLabels.bigChan = machineData.workingChannel;
        screenLabels.smallChan = " ";

        screenLabels.message1 = " ";
        screenLabels.message2 = " ";
        screenLabels.message3 = " ";
    }

    @Override
    public void updateTimerEnded() {
        //context.getScreenLabels().mid2 = MachineUtils.powerLabel(context);
        //context.getScreenLabels().mid3 = MachineUtils.userLabel(context);
    }

    @Override
    public void didReceiveDSC() {

    }

    private String userChannelOrSixteen(MachineData machineData){
        if (machineData.workingChannel == "16" && machineData.userChannel != "16") {
            return machineData.userChannel;
        } else {
            return "16";
        }
    }
}
