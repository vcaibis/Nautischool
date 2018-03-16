package com.example.arjun.vhf_school.ContextAndData.state;

import com.example.arjun.vhf_school.ContextAndData.MachineContext;
import com.example.arjun.vhf_school.ContextAndData.MachineState;
import com.example.arjun.vhf_school.ContextAndData.ScreenLabels;

/**
 * Created by GCI on 16.03.2018.
 */

public class OffState implements MachineState {
    MachineContext context;

    public OffState(MachineContext context) {
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
        this.context = context;
    }

    @Override
    public void updateDisplay() {
        ScreenLabels screenLabels = context.getScreenLabels();

        context.initialize();

        screenLabels.left1 = " ";
        screenLabels.left2 = " ";
        screenLabels.left3 = " ";
        screenLabels.left4 = " ";

        screenLabels.mid1 = " ";
        screenLabels.mid2 = " ";
        screenLabels.mid3 = " ";
        screenLabels.mid4 = " ";

        screenLabels.right1 = " ";
        screenLabels.right2 = " ";
        screenLabels.right3 = " ";
        screenLabels.right4 = " ";

        screenLabels.bigChan = " ";
        screenLabels.smallChan = " ";

        screenLabels.message1 = " ";
        screenLabels.message2 = " ";
        screenLabels.message3 = " ";
    }

    @Override
    public void updateTimerEnded() {

    }

    @Override
    public void didReceiveDSC() {

    }
}
