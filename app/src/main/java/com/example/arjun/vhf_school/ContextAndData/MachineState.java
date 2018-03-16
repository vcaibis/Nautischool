package com.example.arjun.vhf_school.ContextAndData;

/**
 * Created by Helder on 02.03.2018.
 */

/**
 * qui herite MachineControls = Interface State
 */
public interface MachineState extends MachineControls{

    // Handle the alphanumerical buttons
 //   void alphanumeric(int sender);



    // Used to give the context
    void init(MachineContext context);

    // Used to changed the labels value
    void updateDisplay();

    // Used to update some labels
    void updateTimerEnded();

    // Used to prepare to change to ReceiveDSC state
    void didReceiveDSC();

}
