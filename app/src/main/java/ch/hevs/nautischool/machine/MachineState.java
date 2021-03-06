package ch.hevs.nautischool.machine;

/**
 * Created by Helder on 02.03.2018.
 */

/**
 * Generic Interface State which extends MachineControls
 * for all states implemented of machine
 */
public interface MachineState extends MachineControls{

    // Used to give the context
    void init(MachineContext context);

    // Used to changed the labels value
    void updateDisplay();

    // Used to update some labels
    void updateTimerEnded();

    // Used to prepare to change to ReceiveDSC state
    void didReceiveDSC();

}
