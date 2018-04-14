package ch.hevs.nautischool.machine.state.dsc.call;

import java.util.Timer;
import java.util.TimerTask;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.receive.ReceiveState;

/**
 * Created by GCI on 14.04.2018.
 */

public class CallSentState implements MachineState {

    MachineContext context;
    Timer timer;
    public CallSentState(MachineContext context) {
        init(context);
    }


    @Override
    public void alphanumeric(int sender) {
// Nothing to do because no effect
    }

    @Override
    public void sixteen() {
        timerInvalidate();
        context.getMachineData().initCallParam();
        context.sixteenButtonPressed();
    }

    private void timerInvalidate() {
        timer.cancel();
        timer.purge();
    }

    @Override
    public void dualwatch() {
        timerInvalidate();
        context.getMachineData().initCallParam();
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

    @Override
    public void softkey(int sender, boolean longClick) {
// Nothing to do because no effect
    }

    @Override
    public void cancel() {
// Nothing to do because no effect
    }

    @Override
    public void enter() {
// Nothing to do because no effect
    }

    @Override
    public void distress(boolean touchDown) {
        timerInvalidate();
        context.getMachineData().initCallParam();
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
        context.getMachineData().initCallParam();
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

        screenLabels.left1 = " ";
        screenLabels.left2 = " ";
        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.message1 = "SENDING";
        screenLabels.message2 = "DSC CALL";
        screenLabels.message3 = " ";


        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (context.getMachineData().currentType == 0) {
 //                   context.setState(new CallWaitAckState(context));
                } else {
                    context.setState(new ReceiveState(context));
                }
            }
        };
        timer.scheduleAtFixedRate(task, 2000, 1000);
 /*
        timer = Timer.schedule(withTimeInterval: 2, repeats: false, block: { (Timer) in
            // waiting 2 seconds
            if (machineData.currentType == 0) {
                context.setState(new CallWaitAckState(context));
            } else {
                context.setState(new ReceiveState(context));
            }
        })

        let name: DSCName
        let type: DSCType
        var mmsiTo: String? = nil

        switch machineData.currentType {
            case 0:
                name = DSCName.individual
                type = DSCType.routine
                mmsiTo = machineData.currentMMSI
            case 1:
                name = DSCName.ships
                type = DSCType.safety
            case 2:
                name = DSCName.ships
                type = DSCType.urgency
            default:
                name = DSCName.groupcall
                type = DSCType.routine
                mmsiTo = machineData.groupMMSI
        }

        Modele.sharedModele.sendMessage(message: DSCMessage(name: name, type: type, mmsi: machineData.mmsi, channel: machineData.currentChannel, dest: mmsiTo, alertDetails: nil))
  */
    }

    @Override
    public void updateTimerEnded() {
        // Nothing to do because no effect
    }

    @Override
    public void didReceiveDSC() {
        timerInvalidate();
        context.getMachineData().initCallParam();
    }
}
