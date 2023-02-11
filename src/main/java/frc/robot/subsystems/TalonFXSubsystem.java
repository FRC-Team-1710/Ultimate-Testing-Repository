// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TalonFXSubsystem extends SubsystemBase {
  TalonFX motor;
  public double speed = 0;
  /** Creates a new test. */
  public TalonFXSubsystem() {
    motor = new TalonFX(Constants.TalonFXCanID);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void spinMotor(double speed){
    motor.set(TalonFXControlMode.PercentOutput, speed);
    this.speed = speed;
  }

}
