// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class EncoderSubsystem extends SubsystemBase {
  public static DutyCycleEncoder test_encoder;
  private Boolean ENCFAIL;
  public double getA;

  /** Creates a new EncoderSubsystem. */
  public EncoderSubsystem() {
    test_encoder = new DutyCycleEncoder(Constants.encoderPort);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("encoder angle", getAngle());

    if (getAngle() == 0) {
      ENCFAIL = true;
    } else {
      ENCFAIL = false;
    }
    SmartDashboard.putBoolean("ENCODER FAILURE", ENCFAIL);

  }

  public double getAngle() {
    getA = ((test_encoder.get() * 360) % 360);
    return getA;
  }

}
