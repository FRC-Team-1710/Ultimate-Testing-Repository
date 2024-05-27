// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TalonFXSubsystem;

public class TalonFXCommand extends CommandBase {
  /** Creates a new TalonFXCommand. */

  public TalonFXSubsystem m_TalonFXSubsystem;
  public double speedToSet = 0;
  public int speedMode = 0;

  public TalonFXCommand(TalonFXSubsystem talonFXSubsystem, double speedToSet, int speedMode) {
    this.m_TalonFXSubsystem = talonFXSubsystem;
    this.speedToSet = speedToSet;
    this.speedMode += speedMode;

    if (this.speedMode > 1){
      this.speedMode = 1;
    } else if (this.speedMode < -1){
      this.speedMode = -1;
    }

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(talonFXSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (speedMode == 1){
      m_TalonFXSubsystem.spinMotor(speedToSet);
    } else if (speedMode == 0) {
      m_TalonFXSubsystem.spinMotor(0);
    } else if (speedMode == -1){
      m_TalonFXSubsystem.spinMotor(-speedToSet);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
