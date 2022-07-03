// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CalibrateArm;
import frc.robot.commands.ClimbRoutine;
import frc.robot.commands.ManualDrive;
import frc.robot.commands.MoveArm;
import frc.robot.commands.RunFlywheel;
import frc.robot.commands.RunIntake;
import frc.robot.commands.SetArmPosition;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Joysticks
  public static Joystick driveStick = new Joystick(Constants.portZero);
  public static Joystick climbStick = new Joystick(Constants.portOne);

  // Buttons for the drive stick
  JoystickButton shootTrigger = new JoystickButton(driveStick, 1);
  JoystickButton intakeButton = new JoystickButton(driveStick, 2);

  // Climb Joystick Buttons
  JoystickButton calibrateRed = new JoystickButton(climbStick, 7);
  JoystickButton getIntoPosition = new JoystickButton(climbStick, 8);
  JoystickButton calibrateYellow = new JoystickButton(climbStick, 9);
  JoystickButton twoBarClimb = new JoystickButton(climbStick, 10);
  JoystickButton calibrateTilt = new JoystickButton(climbStick, 11);
  JoystickButton fourBarClimb = new JoystickButton(climbStick, 12);
  JoystickButton activateArmMovement = new JoystickButton(climbStick, 1);

  // The robot's subsystems
  Drivetrain drivetrain = new Drivetrain();
  Intake intake = new Intake(-1);
  Shooter shooter = new Shooter(1);
  ArmComponent redArm = new SIMArm(Constants.redArmId, false, true, Constants.redRevInRan, 1, 0, "R");
  ArmComponent yellowArm = new SIMArm(Constants.yellowArmId, false, false, Constants.yellowRevInRan, 2, 3, "Y");
  ArmComponent tiltArm = new NeoArm(Constants.tiltId, true, Constants.tiltRevInRan, "T");

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Default commands for the different subsystems
    drivetrain.setDefaultCommand(new ManualDrive(drivetrain));
    intake.setDefaultCommand(new RunIntake(intake, Constants.defualtIntakeSpeed));
    shooter.setDefaultCommand(new RunFlywheel(shooter, Constants.defualtShooterSpeed));
    redArm.setDefaultCommand(new MoveArm(redArm, Constants.defaultRedArmPosition));
    yellowArm.setDefaultCommand(new MoveArm(yellowArm, Constants.defaultYellowArmPosition));
    tiltArm.setDefaultCommand(new MoveArm(tiltArm, Constants.defaultTiltArmPosition));

    // Button bindings for the different subsystems
    shootTrigger.whenHeld(new RunFlywheel(shooter, Constants.shootTriggerSpeed));
    intakeButton.whenHeld(new RunIntake(intake, Constants.intakeButtonSpeed));
    activateArmMovement.whenHeld(new ParallelCommandGroup(
        new MoveArm(redArm, Constants.redArmAxis),
        new MoveArm(yellowArm, Constants.yellowArmAxis),
        new MoveArm(tiltArm, Constants.tiltArmAxis)
    ));

    // Button bindings for arm calibration and testing
    calibrateRed.whenPressed(new CalibrateArm(redArm));
    getIntoPosition.whenHeld(new SetArmPosition(redArm, 1, false));
    calibrateYellow.whenPressed(new CalibrateArm(yellowArm));
    twoBarClimb.whenHeld(new SetArmPosition(redArm, 0, true));
    calibrateTilt.whenPressed(new CalibrateArm(tiltArm));
    fourBarClimb.whenHeld(new ClimbRoutine(redArm, yellowArm, tiltArm));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Autonomous command is yet to be developed
    return null;
  }
}
