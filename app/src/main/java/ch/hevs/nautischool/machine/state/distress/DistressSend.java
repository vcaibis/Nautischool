package ch.hevs.nautischool.machine.state.distress;

import java.util.Timer;
import java.util.TimerTask;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.ScreenLabels;

/**
 * Created by GCI on 21.04.2018.
 */

public class DistressSend implements MachineState {
    MachineContext context;
    Timer timer;
    int counter = 6;
    boolean isSending = false;
    MachineState myState = this;
    public DistressSend(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {
// Nothing to do because no effect
    }

    @Override
    public void sixteen() {
// Nothing to do because no effect
    }

    @Override
    public void dualwatch() {
// Nothing to do because no effect
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
        if (!touchDown && !isSending) {
            timerInvalidate();
            context.setState(new DistressState(context));
        }
    }

    private void timerInvalidate() {
        timer.cancel();
        timer.purge();
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
// Nothing to do because no effect
    }

    @Override
    public void init(MachineContext context) {
        this.context = context;
    }

    @Override
    public void updateDisplay() {
        ScreenLabels screenLabels = context.getScreenLabels();

        if (timer == null) {
            // waiting 5 seconds
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println(counter);
                    counter--;
                    if (counter == 0) {
                        isSending = true;
                        timerInvalidate();
                    } else if (context.getMachineData().isBeepOn) {
                        context.playSound(0);
                    }
                    context.setState(myState);
                }
            };
            timer.scheduleAtFixedRate(task, 0,1000);
            /*
            timer = Timer.scheduledTimer(withTimeInterval: 1, repeats: true, block: { (Timer) in
                self.counter -= 1
                if self.counter == 0 {
                    self.isSending = true
                    self.timer!.invalidate()
                } else if self.context.machineData.isBeepOn {
                    self.context.playSound(soundID: 0)
                }
                self.context.setState(state: self)
            })
            */
        }

        if (counter == 0) {
            MachineData machineData = context.getMachineData();
   //         Modele.sharedModele.sendMessage(new DSCMessage(DSCName.distress, DSCType.alert, machineData.mmsi, "Ch16", null, alertDetails: DistressDetails(latitude: machineData.latitude, longitude: machineData.longitude, utc: machineData.utc, mmsi: machineData.mmsi, type: DistressType(rawValue: machineData.distressType[machineData.currentDistressType])!)))
  //          timer = Timer.scheduledTimer(withTimeInterval: 2, repeats: false, block: { (Timer) in
 //               this.context.setState(new DistressWaitReply(this));
            //})
        }

        screenLabels.left1 = " ";
        screenLabels.left2 = " ";
        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.right1 = " ";
        screenLabels.right2 = " ";
        screenLabels.right3 = " ";
        screenLabels.right4 = " ";

        screenLabels.smallChan = " ";

        screenLabels.message1 = (counter != 0 ? "DISTRESS ALERT" : "SENDING");
        screenLabels.message2 = (counter != 0 ? "Sending in" : "DISTRESS ALERT");
        screenLabels.message3 = (counter != 0 ? ""+(counter)+" sec" : " ");
        this.context.setScreenLabels(screenLabels);
    }

    @Override
    public void updateTimerEnded() {
// Nothing to do because no effect
    }

    @Override
    public void didReceiveDSC() {
        timerInvalidate();
    }
}
