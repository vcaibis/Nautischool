package ch.hevs.nautischool.machine;

/**
 * Created by GCI on 16.03.2018.
 */

/**
 * Interface implemented by MachineState for standard control of machine
 */
public interface MachineControls {

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

}
