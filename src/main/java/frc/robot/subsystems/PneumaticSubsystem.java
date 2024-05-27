// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import frc.robot.Constants;

public class PneumaticSubsystem extends SubsystemBase {

  public DoubleSolenoid alphaSolenoid = new DoubleSolenoid(Constants.pcmPort, PneumaticsModuleType.CTREPCM, Constants.kPneuForwardPort, Constants.kPneuReversePort);
  public DoubleSolenoid bravoSolenoid = new DoubleSolenoid(Constants.pcmPort, PneumaticsModuleType.CTREPCM, Constants.kPneu2ForwardPort, Constants.kPneu2ReversePort);
  
  boolean bothPneuForward;
  
  /** Creates a new PneuSubsystem. */
  public PneumaticSubsystem() {

    // Sets all solenoids to the same position
    alphaSolenoid.set(kReverse);
    bravoSolenoid.set(kReverse);
    bothPneuForward = false;

  }

  @Override
  public void periodic() {  
    // This method will be called once per scheduler run
  }

  
  public void SetOneSolenoidReverse() {

    alphaSolenoid.set(kReverse);

  }

  public void SetOneSolenoidForward() {

    alphaSolenoid.set(kForward);

  }

  public void SetTwoSolenoidsForward(){

    alphaSolenoid.set(kForward);
    bravoSolenoid.set(kForward);
    bothPneuForward = true;

  }

  public void SetTwoSolenoidsReverse(){
    
    alphaSolenoid.set(kReverse);
    bravoSolenoid.set(kReverse);
    bothPneuForward = false;

  }

  public void ToggleOneSolenoid(){
    alphaSolenoid.toggle();
  }

  public void ToggleTwoSolenoids() {

    alphaSolenoid.toggle();
    bravoSolenoid.toggle();

  }

}


