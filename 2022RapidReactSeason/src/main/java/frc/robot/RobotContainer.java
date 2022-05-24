// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CalibrateArm;
import frc.robot.commands.ManualDrive;
import frc.robot.commands.MoveArm;
import frc.robot.commands.RunFlywheel;
import frc.robot.commands.RunIntake;
import frc.robot.commands.SetExtentionIntakeMode;
import frc.robot.commands.ShootBalls;
import frc.robot.commands.TestArmMovement;
import frc.robot.commands.TestAutoDrive;
import frc.robot.subsystems.ArmComponent;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExtendedIntake;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  Joystick driveStick = new Joystick(0);
  Joystick climbStick = new Joystick(1);

  JoystickButton shootTrigger = new JoystickButton(driveStick, 1);
  JoystickButton intakeButton = new JoystickButton(driveStick, 2);
  JoystickButton extentionButton = new JoystickButton(driveStick, 7);
  JoystickButton calibrateArmsButton = new JoystickButton(driveStick, 8);
  JoystickButton testKinematicsButton = new JoystickButton(driveStick, 9);
  JoystickButton testClimbCalibration = new JoystickButton(driveStick, 10);

  Drivetrain drivetrain = new Drivetrain();
  Intake intake = new Intake(-1);
  Shooter shooter = new Shooter(1);
  ArmComponent redArm = new ArmComponent(Constants.redArmId, MotorType.kBrushed, 1, Constants.redRevInRan);
  ArmComponent yellowArm = new ArmComponent(Constants.yellowArmId, MotorType.kBrushed, 1, Constants.yellowRevInRan);
  ArmComponent tiltArm = new ArmComponent(Constants.tiltId, MotorType.kBrushless, 1, Constants.tiltRevInRan);
  ExtendedIntake extIntake = new ExtendedIntake();


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
    drivetrain.setDefaultCommand(new ManualDrive(drivetrain, driveStick));
    intake.setDefaultCommand(new RunIntake(intake, 1000000, 0));
    shooter.setDefaultCommand(new RunFlywheel(shooter, 1000000, 0));
    redArm.setDefaultCommand(new MoveArm(redArm, climbStick, "Y"));
    yellowArm.setDefaultCommand(new MoveArm(yellowArm, climbStick, "X"));
    tiltArm.setDefaultCommand(new MoveArm(tiltArm, climbStick, "Z"));
    extIntake.setDefaultCommand(new SetExtentionIntakeMode(extIntake, false));

    shootTrigger.whenPressed(new ShootBalls(shooter, intake, 2000));
    intakeButton.whenPressed(new RunIntake(intake, 1000000, -0.5));
    extentionButton.whenPressed(new SetExtentionIntakeMode(extIntake, true));
    calibrateArmsButton.whenPressed(new CalibrateArm(redArm));
    testKinematicsButton.whenPressed(new TestAutoDrive(drivetrain));
    testClimbCalibration.whenPressed(new TestArmMovement(redArm));
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
