package ch.hevs.nautischool.machine.state.dsc.log;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.dsc.MenuDSCState;

/**
 * Created by GCI on 17.03.2018.
 */

public class LogState implements MachineState {
    MachineContext context;
    boolean currentPage = true;
    int currentLog = 0;

    public LogState(MachineContext context) {
        init(context);
    }
    @Override
    public void alphanumeric(int sender) {
// Nothing to do because no effect
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
// Nothing to do because no effect
    }
    /**
     * Method to Manage the softkeys and change the context state
     * @param sender
     * @param longClick
     */
    @Override
    public void softkey(int sender, boolean longClick) {
        if (!longClick) {
            MachineData machineData = context.getMachineData();
            if (sender == 1) {
                context.navigateBackToMenuDSCState();
            } else if (sender == 2 && currentLog > 0) {
                currentLog -= 1;
                currentPage = true;
                context.setState(this);
            } else if (sender == 3 && currentLog < machineData.alertLogs.size()-1) {
                currentLog += 1;
                currentPage = true;
                context.setState(this);
            } else if (sender == 4 && machineData.alertLogs.get(currentLog).getAlertDetails() != null) {
                currentPage = !currentPage;
                context.setState(this);
            }
        }

    }

    @Override
    public void cancel() {
        context.setState(new MenuDSCState(context));
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

        screenLabels.left1 = (currentPage ? machineData.alertLogs.get(currentLog).getName() : machineData.alertLogs.get(currentLog).getAlertDetails().distressType);
        screenLabels.left2 = (currentPage ? machineData.alertLogs.get(currentLog).getType() : machineData.alertLogs.get(currentLog).getAlertDetails().longitude);
        screenLabels.left3 = (currentPage ? "From" : machineData.alertLogs.get(currentLog).getAlertDetails().latitude);
        screenLabels.left4 = (currentPage ? machineData.alertLogs.get(currentLog).getMmsi() : machineData.alertLogs.get(currentLog).getAlertDetails().utc);

        screenLabels.mid4 = " ";

        screenLabels.right1 = "DSC";
        screenLabels.right2 = (currentLog == 0 ? " " : "Back");
        screenLabels.right3 = (currentLog == machineData.alertLogs.size()-1 ? " " : "Next");

        if (machineData.alertLogs.get(currentLog).getAlertDetails() != null) {
            screenLabels.right4 = (currentPage ? "\u25BC" : "\u25B2");
        } else {
            screenLabels.right4 = " ";
        }

        screenLabels.smallChan = " ";
    }

    @Override
    public void updateTimerEnded() {
// Nothing to do because no effect
    }

    @Override
    public void didReceiveDSC() {
// Nothing to do because no effect
    }
}
