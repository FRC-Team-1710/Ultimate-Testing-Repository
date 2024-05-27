// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.music.Orchestra;

import java.io.File;
import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/* TODO: Rewrite this class with updated CTRE API */

public class OrchestraSubsystem extends SubsystemBase {
  /** Creates a new OrchestraSubsystem. */

  WPI_TalonFX[] _fxes = {
      //new WPI_TalonFX(50, "rio"),
      //new WPI_TalonFX(6, "rio"),

      new WPI_TalonFX(12, "carnivore uno"),
      new WPI_TalonFX(10, "carnivore uno"),
      new WPI_TalonFX(3, "carnivore uno"),
      new WPI_TalonFX(1, "carnivore uno"),
      new WPI_TalonFX(9, "carnivore uno"),
      new WPI_TalonFX(7, "carnivore uno"),
      new WPI_TalonFX(6, "carnivore uno"),
      new WPI_TalonFX(4, "carnivore uno")
  };
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
    /* A list of TalonFXs that are to be used as instruments */
    ArrayList<TalonFX> _instruments = new ArrayList<TalonFX>();

    /* Initialize the TalonFXs to be used */
    for (int i = 0; i < _fxes.length; ++i) {
      _instruments.add(_fxes[i]);
    }

    /* Create the orchestra with the TalonFX instruments */
    _orchestra = new Orchestra(_instruments);

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
