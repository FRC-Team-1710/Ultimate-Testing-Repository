// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  /* Pneumatics Constants */
  public static final int phPort = 1;
  public static final int pcmPort = 1;
  public static final int kPneuForwardPort = 2;
  public static final int kPneuReversePort = 5;
  public static final int kPneu2ForwardPort = 0;
  public static final int kPneu2ReversePort = 7;

  /* TalonFX Constants */
  public static final int TalonFXCanID = 50;

  /* Canivore */
  public static final String canivore = "uno";

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  /* Encoder Constants */
  public static final int encoderPort = 4;
}
