package com.example.arjun.vhf_school.ContextAndData;

/**
 * Created by Helder on 02.03.2018.
 */

/**
 * qui herite MachineControls = Interface State
 */
public interface MachineState {

    // Handle the alphanumerical buttons
    void alphanumeric(int sender);

    // Handle the sixteen button
    void sixteen();

    // Handle the D/W button
    void dualwatch();

    // Handle the Light button
    void light();

    // Handle the power button
    void power();

    // Handle the softkey button getting which one called this method
    void softkey(int sender, boolean longClick);

    // Handle the cancel button
    void cancel();

    // Handle the enter button
    void enter();

    // Handle the distress button
    void distress(boolean touchDown);

    // Handle the volume as parameter
    void volume(int sender);

    // Handle the squelch as parameter
    void squelch(int sender);

    // Handle the ptt button
    void ptt();


    // Used to give the context
    void init(MachineContext context);

    // Used to changed the labels value
    void updateDisplay();

    // Used to update some labels
    void updateTimerEnded();

    // Used to prepare to change to ReceiveDSC state
    void didReceiveDSC();

}
