package ch.hevs.nautischool.machine.state.dsc.menu2;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.MachineState;

/**
 * Created by Helder on 14.04.2018.
 */

public class DirEditState implements MachineState {
    MachineContext context;

    public DirEditState(MachineContext context) {init(context);
    }

    @Override
    public void alphanumeric(int sender) {

    }

    @Override
    public void sixteen() {

    }

    @Override
    public void dualwatch() {

    }

    @Override
    public void light() {

    }

    @Override
    public void power() {

    }

    @Override
    public void softkey(int sender, boolean longClick) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void enter() {

    }

    @Override
    public void distress(boolean touchDown) {

    }

    @Override
    public void volume(int sender) {

    }

    @Override
    public void squelch(int sender) {

    }

    @Override
    public void ptt() {

    }

    @Override
    public void init(MachineContext context) {
        this.context = context;
    }

    @Override
    public void updateDisplay() {

    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {

    }
}
