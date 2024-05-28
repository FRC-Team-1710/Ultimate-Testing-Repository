// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.LedCommand;
import frc.robot.commands.TalonFXCommand;
import frc.robot.subsystems.EncoderSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.OrchestraSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.TalonFXSubsystem;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  /* Controllers */
  private final Joystick controller = new Joystick(0);

  /* Driver Buttons */
  private final JoystickButton toggleClaw = new JoystickButton(controller, XboxController.Button.kRightBumper.value);

  private final JoystickButton startMusic = new JoystickButton(controller, XboxController.Button.kStart.value);
  private final JoystickButton toggleSongIsPaused = new JoystickButton(controller, XboxController.Button.kB.value);
  private final JoystickButton toggleSongIsStopped = new JoystickButton(controller, XboxController.Button.kA.value);
  private final JoystickButton nextSong = new JoystickButton(controller, XboxController.Button.kRightBumper.value);
  private final JoystickButton lastSong = new JoystickButton(controller, XboxController.Button.kLeftBumper.value);

  private final JoystickButton spinMotor = new JoystickButton(controller, XboxController.Button.kA.value);
  private final JoystickButton unSpinMotor = new JoystickButton(controller, XboxController.Button.kY.value);
  private final JoystickButton stopMotor = new JoystickButton(controller, XboxController.Button.kB.value);

  private final JoystickButton toggleConeOrCube = new JoystickButton(controller, XboxController.Button.kA.value);
  private final JoystickButton toggleWantsOrHas = new JoystickButton(controller, XboxController.Button.kB.value);

  private final JoystickButton resetEncoder = new JoystickButton(controller, XboxController.Button.kA.value);

  /* Subsystems */
  private final PneumaticSubsystem m_PneumaticSubsystem = new PneumaticSubsystem();
  private final LedSubsystem m_LedSubsystem = new LedSubsystem();
  private final OrchestraSubsystem m_OrchestraSubsystem = new OrchestraSubsystem();
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final TalonFXSubsystem m_TalonFXSubsystem = new TalonFXSubsystem();
  private final EncoderSubsystem m_EncoderSubsystem = new EncoderSubsystem();

  /* Test Select */
  private final boolean testingLeds = false;
  private final boolean testingOrchestra = false;
  private final boolean testingPneumatics = false;
  private final boolean testingTalonFX = false;
  private final boolean testingEncoders = true;

  /* Misc */
  public boolean motorIsSpinning = false;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    if (testingLeds){
      m_LedSubsystem.setDefaultCommand(new LedCommand(m_LedSubsystem));
    }

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    /* Music Bindings */
    if (testingOrchestra) {
      startMusic.onTrue(new InstantCommand(() -> m_OrchestraSubsystem.startMusic()));
      toggleSongIsPaused.onTrue(new InstantCommand(() -> m_OrchestraSubsystem.toggleSongIsPaused()));
      toggleSongIsStopped.onTrue(new InstantCommand(() -> m_OrchestraSubsystem.toggleSongIsStopped()));
      nextSong.onTrue(new InstantCommand(() -> m_OrchestraSubsystem.nextSong()));
      lastSong.onTrue(new InstantCommand(() -> m_OrchestraSubsystem.lastSong()));
    }

    /* Pneumatics Bindings */
    if (testingPneumatics) {
      toggleClaw.onTrue(new InstantCommand(() -> m_PneumaticSubsystem.ToggleTwoSolenoids()));
    }

    /* Motor Bindings */
    // Use commands when dealing with motors, as InstantCommands have no protection against motor damage (see: Oblarg's best programming practices on ChiefDelphi)
    if (testingTalonFX) {
      double motorSpeed = .5;

      spinMotor.onTrue(new TalonFXCommand(m_TalonFXSubsystem, motorSpeed));
      unSpinMotor.and(spinMotor.negate()).onTrue(new TalonFXCommand(m_TalonFXSubsystem, -motorSpeed));

      spinMotor.or(unSpinMotor).onFalse(new TalonFXCommand(m_TalonFXSubsystem, 0));
    }

    /* LED Bindings */
    if (testingLeds) {
      toggleConeOrCube.onTrue(new InstantCommand(() -> m_LedSubsystem.ToggleConeOrCubeColor()));
      toggleWantsOrHas.onTrue(new InstantCommand(() -> m_LedSubsystem.ToggleBlink()));
    }

    /* Encoder Bindings */
    if (testingEncoders){
      resetEncoder.onTrue(new InstantCommand(() -> m_EncoderSubsystem.resetEncoder()));
    }

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
