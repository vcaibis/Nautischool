package ch.hevs.nautischool.machine.state.dsc;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.log.LogState;
import ch.hevs.nautischool.machine.state.dsc.menu1.Menu1State;
import ch.hevs.nautischool.machine.state.receive.DualWatchState;
import ch.hevs.nautischool.machine.state.receive.MemoryScanState;
import ch.hevs.nautischool.machine.state.receive.ReceiveState;
import ch.hevs.nautischool.machine.state.receive.ScanState;
import ch.hevs.nautischool.machine.state.receive.TriWatchState;

/**
 * Created by Helder on 23.03.2018.
 */
public class MenuDSCState  implements MachineState {
    MachineContext context;

    // Constructor
    public MenuDSCState(MachineContext context) {
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
            switch (sender) {
                case 1 :
                    navigateBackToRad();
                    break;
                case 2 :
                    context.setState(new CallState(context));
                    break;
                case 3 :
                    context.setState(new LogState(context));
                    break;
                default:
                    context.setState(new Menu1State(context));
            }
        }

    }
    private void navigateBackToRad() {
        switch (context.getMachineData().currentMode) {
            case MachineData.MACHINEMODE_DUALWATCH: //dualwatch:
                context.setState(new DualWatchState(context));
                break;
            case MachineData.MACHINEMODE_MEMORYSCAN://.memoryscan:
                context.setState(new MemoryScanState(context));
                break;
            case MachineData.MACHINEMODE_SCAN://.scan:
                context.setState(new ScanState(context));
                break;
            case MachineData.MACHINEMODE_TRIWATCH: // .triwatch:
                context.setState(new TriWatchState(context));
                break;
            default:
                context.setState(new ReceiveState(context));

        }
    }

    @Override
    public void cancel() {
        navigateBackToRad();
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

        screenLabels.left1 = machineData.longitude;
        screenLabels.left2 = machineData.latitude;
        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.mid1 = " ";
        screenLabels.mid2 = " ";
        screenLabels.mid3 = " ";
        screenLabels.mid4 = machineData.currentMode;

        screenLabels.right1 = "Rad";
        screenLabels.right2 = "Call";
        screenLabels.right3 = "Log";
        screenLabels.right4 = "Menu";

        screenLabels.bigChan = " ";
        screenLabels.smallChan = machineData.workingChannel;

        screenLabels.message1 = " ";
        screenLabels.message2 = " ";
        screenLabels.message3 = " ";

        // set labels of screen
        context.setScreenLabels(screenLabels);
    }

    @Override
    public void updateTimerEnded() {
        context.getScreenLabels().smallChan = context.getMachineData().workingChannel;
    }

    @Override
    public void didReceiveDSC() {

    }
}
