// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CalibrateArm;
import frc.robot.commands.ManualDrive;
import frc.robot.commands.MoveArm;
import frc.robot.commands.RunFlywheel;
import frc.robot.commands.RunIntake;
import frc.robot.commands.TestArmMovement;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static Joystick driveStick = new Joystick(0);
  public static Joystick climbStick = new Joystick(1);

  JoystickButton shootTrigger = new JoystickButton(driveStick, 1);
  JoystickButton intakeButton = new JoystickButton(driveStick, 2);
  JoystickButton extentionButton = new JoystickButton(driveStick, 7);
  JoystickButton calibrateArmsButton = new JoystickButton(climbStick, 7);
  JoystickButton testClimbCalibration = new JoystickButton(climbStick, 8);

  Drivetrain drivetrain = new Drivetrain();
  Intake intake = new Intake(-1);
  Shooter shooter = new Shooter(1);
  ArmComponent redArm = new SIMArm(Constants.redArmId, 1, Constants.redRevInRan, 1, 0);
  ArmComponent yellowArm = new SIMArm(Constants.yellowArmId, 1, Constants.yellowRevInRan, 1, 2);
  ArmComponent tiltArm = new NeoArm(Constants.tiltId, 1, Constants.tiltRevInRan);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    drivetrain.setDefaultCommand(new ManualDrive(drivetrain));
    intake.setDefaultCommand(new RunIntake(intake, 0));
    shooter.setDefaultCommand(new RunFlywheel(shooter, 0));
    redArm.setDefaultCommand(new MoveArm(redArm, "Y"));
    yellowArm.setDefaultCommand(new MoveArm(yellowArm, "X"));
    tiltArm.setDefaultCommand(new MoveArm(tiltArm, "Z"));

    shootTrigger.whenHeld(new RunFlywheel(shooter, 0.7));
    intakeButton.whenHeld(new RunIntake(intake, 0.5));
    calibrateArmsButton.whenPressed(new CalibrateArm(redArm));
    testClimbCalibration.whenHeld(new TestArmMovement(redArm));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
