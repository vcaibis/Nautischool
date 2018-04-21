package ch.hevs.nautischool.machine.state.dsc.menu1;

import java.util.Timer;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineData;
import ch.hevs.nautischool.machine.MachineState;
import ch.hevs.nautischool.machine.MachineUtils;
import ch.hevs.nautischool.machine.ScreenLabels;
import ch.hevs.nautischool.machine.state.OffState;

/**
 * Created by Helder on 14.04.2018.
 */

public class PosnEditPosn implements MachineState {
    MachineContext context;
    String currentLongitude = "--°--+";
    String currentLatitude= "---°--+";
    int currentIndex= 0;
    Boolean isLongitude= true;
    Boolean isTwinkled= false;
    Timer timer;

    public PosnEditPosn(MachineContext context) {
        init(context);
    }

    @Override
    public void alphanumeric(int sender) {

        if (isLongitude && currentIndex < 5) {
            currentLongitude = MachineUtils.replaceSubrange(currentLongitude, currentIndex, ""+sender);
            currentIndex += (currentIndex != 1 ? 1 : 2);
            context.setState(this);
        } else if (!isLongitude && currentIndex < 6) {
            currentLatitude = MachineUtils.replaceSubrange(currentLatitude, currentIndex, ""+sender);
            currentIndex += (currentIndex != 2 ? 1 : 2);
            context.setState(this);
        }
    }

    @Override
    public void sixteen() {
    //    timer!.invalidate()
        context.sixteenButtonPressed();
    }

    @Override
    public void dualwatch() {
     //   timer!.invalidate()
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
            //    timer!.invalidate()
                context.navigateBackToMenuDSCState();
            } else if (sender == 2) {
                if (!isLongitude || currentIndex != 0) {
                    if (isLongitude) {
                        currentIndex -= (currentIndex != 3 ? 1 : 2);
                        if (currentIndex < 4) {
                            currentLongitude = MachineUtils.replaceSubrange(currentLongitude, currentIndex, (currentIndex == 1 ? "-°-" : "--"));
                            context.setState(this);
                        } else {
                            currentLongitude = MachineUtils.replaceSubrange(currentLongitude, currentIndex, "-+");
                            context.setState(this);
                        }
                    } else {
                        currentIndex -= (currentIndex != 4 ? 1 : 2);
                        if (currentIndex == -1) {
                            isLongitude = !isLongitude;
                            currentIndex = 5;
                            currentLongitude = MachineUtils.replaceSubrange(currentLongitude, currentIndex, "+");
                            context.setState(this);
                        } else if (currentIndex < 5) {
                            currentLatitude = MachineUtils.replaceSubrange( currentLatitude,  currentIndex,  (currentIndex == 2 ? "-°-" : "--"));
                            context.setState(this);
                        } else if (currentIndex == 5) {
                            currentLatitude = MachineUtils.replaceSubrange(currentLatitude, currentIndex,  "-+");
                            context.setState(this);
                        } else {
                            currentLatitude = MachineUtils.replaceSubrange(currentLatitude, currentIndex, "+");
                            context.setState(this);
                            initializeTimer();
                        }
                    }
                }
            } else if (sender == 3) {
                if (isLongitude && currentIndex == 5) {
                    currentLongitude = MachineUtils.replaceSubrange(currentLongitude, currentIndex, "N");
                    currentIndex = 0;
                    isLongitude = !isLongitude;
                    context.setState(this);
                } else if (!isLongitude && currentIndex == 6) {
                    currentLatitude = MachineUtils.replaceSubrange(currentLatitude, currentIndex,  "E");
                    currentIndex += 1;
                 //   timer!.invalidate()
                    context.setState(this);
                } else if (isLongitude && currentIndex == 0) {
                 //   timer!.invalidate()
                    context.setState(new PosnEditUTC(context));
                }
            } else {
                if (isLongitude && currentIndex == 5) {
                    currentLongitude = MachineUtils.replaceSubrange( currentLongitude,  currentIndex,  "S");
                    currentIndex = 0;
                    isLongitude = !isLongitude;
                    context.setState(this);
                } else if (!isLongitude && currentIndex == 6) {
                    currentLatitude = MachineUtils.replaceSubrange( currentLatitude,  currentIndex,  "W");
                    currentIndex += 1;
                 //   timer!.invalidate()
                    context.setState(this);
                }
            }
        }
    }

    @Override
    public void cancel() {
     //   timer!.invalidate()
        context.setState(new PosnState(context));
    }

    @Override
    public void enter() {
        if (!isLongitude && currentIndex == 7) {
            if (isNewPosnValid()) {
             //   timer?.invalidate()
                context.getMachineData().longitude = currentLongitude;
                context.getMachineData().latitude = currentLatitude;
                context.setState(new PosnEditUTC(context));
            } else {
                currentLongitude = "--°--+";
                currentLatitude = "---°--+";
                isLongitude = !isLongitude;
                currentIndex = 0;
                timer = null;
                context.setState(this);
            }
        }
    }

    @Override
    public void distress(boolean touchDown) {
     //   timer!.invalidate()
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
    //    timer!.invalidate()
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

        if (timer == null) {
            initializeTimer();
        }

        screenLabels.left1 = currentLongitude;
        screenLabels.left2 = currentLatitude;
        screenLabels.left3 = machineData.utc;

        if (isLongitude) {
            if (currentIndex == 0) {
                screenLabels.right2 = " ";
                screenLabels.right3 = "UTC";
            } else if (currentIndex == 5) {
                screenLabels.right3 = "N";
                screenLabels.right4 = "S";
            } else {
                screenLabels.right2 = "\u25C0";
                screenLabels.right3 = " ";
                screenLabels.right4 = " ";
            }
        } else {
            if (currentIndex == 0) {
                screenLabels.right2 = "\u25C0";
                screenLabels.right3 = " ";
                screenLabels.right4 = " ";
            } else if (currentIndex == 6) {
                screenLabels.right3 = "E";
                screenLabels.right4 = "W";
            } else {
                screenLabels.right2 = "\u25C0";
                screenLabels.right3 = " ";
                screenLabels.right4 = " ";
            }
        }
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {
     //   timer!.invalidate()
    }

    private void initializeTimer(){
    /*    timer = Timer.scheduledTimer(withTimeInterval: 0.25, repeats: true, block: { (Timer) in
            if self.isLongitude {
                if self.currentIndex == 5 {
                    self.currentLongitude = MachineUtils.replaceSubrange(of: self.currentLongitude, at: self.currentIndex, with: (self.isTwinkled ? " " : "+"))
                } else {
                    self.currentLongitude = MachineUtils.replaceSubrange(of: self.currentLongitude, at: self.currentIndex, with: (self.isTwinkled ? " " : "-"))
                }
            } else {
                if self.currentIndex == 6 {
                    self.currentLatitude = MachineUtils.replaceSubrange(of: self.currentLatitude, at: self.currentIndex, with: (self.isTwinkled ? " " : "+"))
                } else {
                    self.currentLatitude = MachineUtils.replaceSubrange(of: self.currentLatitude, at: self.currentIndex, with: (self.isTwinkled ? " " : "-"))
                }
            }
            self.isTwinkled = !self.isTwinkled
            self.context.setState(state: self)
        })
        */
    }

    private Boolean isNewPosnValid(){

        int longitudeDegree = Integer.parseInt(currentLongitude.substring(0, 2));
        int longitudeMinute = Integer.parseInt(currentLongitude.substring(3, 5));
        int latitudeDegree = Integer.parseInt(currentLatitude.substring(0, 3));
        int latitudeMinute = Integer.parseInt(currentLatitude.substring(4, 6));
        if(longitudeDegree > 90) return false;
        if(longitudeDegree == 90 && longitudeMinute != 0) return false;
        if(latitudeDegree > 180) return false;
        if(latitudeDegree == 180 && latitudeMinute != 0) return false;

        return true;
    }
}
