package com.example.arjun.vhf_school.machine.state.distress;

import com.example.arjun.vhf_school.machine.MachineContext;
import com.example.arjun.vhf_school.machine.MachineState;

/**
 * Created by GCI on 17.03.2018.
 */

public class DistressState implements MachineState {
    MachineContext context;

    public DistressState(MachineContext context) {
        init(context);
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
