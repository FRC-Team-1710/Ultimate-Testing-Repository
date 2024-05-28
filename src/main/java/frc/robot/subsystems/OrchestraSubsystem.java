// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;

public class OrchestraSubsystem extends SubsystemBase {
  /** Creates a new OrchestraSubsystem. */

  TalonFX alphaMotor = new TalonFX(12, Constants.canivore);
  TalonFX bravoMotor = new TalonFX(10, Constants.canivore);
  TalonFX charlieMotor = new TalonFX(3, Constants.canivore);
  TalonFX deltaMotor = new TalonFX(1, Constants.canivore);
  TalonFX echoMotor = new TalonFX(9, Constants.canivore);
  TalonFX foxtrotMotor = new TalonFX(7, Constants.canivore);
  TalonFX golfMotor = new TalonFX(6, Constants.canivore);
  TalonFX hotelMotor = new TalonFX(4, Constants.canivore);

  Orchestra _orchestra;

  /*
   * File file = new File("C:" + File.separator + "Users" + File.separator +
   * "168mra26" + File.separator + "Programming"
   * + File.separator + "Orchestra Test" + File.separator + "src" + File.separator
   * + "main" + File.separator + "deploy"
   * + File.separator + "la cucaracha car horn.chrp");
   */

  /* An array of songs to be played */
  String[] _songs = new String[] {
      "BetterLaCucaracha",
      "Stars and Stripes Forever",
      "Danger Zone",
      "BewareTheForest'sMushrooms",
      "Super Mario Bros Theme",
      "WeLikeFortnite",
      "Collective Consciousness"
  };

  /* track which song is selected for play */
  int _songSelection = 0;

  /* overlapped actions */
  int _timeToPlayLoops = 0;

  /* joystick vars */
  int _lastButton = 0;
  int _lastPOV = 0;

  public OrchestraSubsystem() {

    /* Create the orchestra with the TalonFX instruments */
    _orchestra.addInstrument(alphaMotor, 0);
    _orchestra.addInstrument(bravoMotor, 1);
    _orchestra.addInstrument(charlieMotor, 2);
    _orchestra.addInstrument(deltaMotor, 3);
    _orchestra.addInstrument(echoMotor, 5);
    _orchestra.addInstrument(golfMotor, 6);
    _orchestra.addInstrument(hotelMotor, 7);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (_timeToPlayLoops > 0) {
      --_timeToPlayLoops;
      if (_timeToPlayLoops == 0) {
        /* scheduled play request */
        System.out.println("Auto-playing song.");
        _orchestra.play();
      }
    }
  }

  public void startMusic() {
    LoadMusicSelection(0);
  }

  public void nextSong() {
    LoadMusicSelection(+1);
  }

  public void lastSong() {
    LoadMusicSelection(-1);
  }

  public void toggleSongIsPaused() {
    if (_orchestra.isPlaying()) {
      _orchestra.pause();
      System.out.println("Song paused");
    } else {
      _orchestra.play();
      System.out.println("Playing song...");
    }
  }

  public void toggleSongIsStopped() {
    if (_orchestra.isPlaying()) {
      _orchestra.stop();
      System.out.println("Song stopped.");
    } else {
      _orchestra.play();
      System.out.println("Playing song...");
    }
  }

  public void LoadMusicSelection(int offset) {
    /* increment song selection */
    _songSelection += offset;
    /* wrap song index in case it exceeds boundary */
    if (_songSelection >= _songs.length) {
      _songSelection = 0;
    }
    if (_songSelection < 0) {
      _songSelection = _songs.length - 1;
    }
    /* load the chirp file */
    // _orchestra.loadMusic(_songs[_songSelection])
    /*
     * File file = new File("C:" + File.separator + "Users" + File.separator +
     * "168mra26" + File.separator + "Programming"
     * + File.separator + "Orchestra Test" + File.separator + "src" + File.separator
     * + "main" + File.separator
     * + "deploy" + File.separator + _songs[_songSelection]);
     */

    _orchestra.loadMusic(Filesystem.getDeployDirectory().toPath().resolve(
        _songs[_songSelection] + ".chrp").toString());

    /* print to console */
    System.out.println("Song selected is: " + _songs[_songSelection] + ".  Press left/right bumpers to change.");

    /*
     * schedule a play request, after a delay.
     * This gives the Orchestra service time to parse chirp file.
     * If play() is called immedietely after, you may get an invalid action error
     * code.
     */
    _timeToPlayLoops = 10;
  }

}
