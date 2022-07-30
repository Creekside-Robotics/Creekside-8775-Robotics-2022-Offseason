// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class FieldOrientedDrive extends CommandBase {
  /** Creates a new FieldOrientedDrive. */
  private Drivetrain drivetrain;
  private int reverseButtonNumber;
  private PIDController angleController; //will need to be tuned
  private double minNormDist = 0.1;
  private Rotation2d goalAngle = new Rotation2d();

  public FieldOrientedDrive(Drivetrain drivetrain, int reverseButtonNumber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.reverseButtonNumber = reverseButtonNumber;
    this.angleController = new PIDController(0.2, 0, 0.2);
    addRequirements(this.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Translation2d joystickTranslation2d = new Translation2d(-RobotContainer.driveStick.getY(), -RobotContainer.driveStick.getX()); //Gets translation of the joystick in field relative corrdinates
    double normDist = joystickTranslation2d.getNorm();
    if (normDist > this.minNormDist) {
      this.goalAngle = new Rotation2d(joystickTranslation2d.getX(), joystickTranslation2d.getY());
    }

    double rotationalDifference = angleRelativeToGoalAngle();
    double turningOutput = this.angleController.calculate(rotationalDifference, 0);
    double speedOutput = getSpeedOutput(normDist, rotationalDifference);
    drivetrain.setMovement(speedOutput, -turningOutput); //Revering turn rate since in mathmatics counterclockwise is positive.
  }

  private double angleRelativeToGoalAngle() {
    Rotation2d driveAngle = this.drivetrain.getRotation();
    Rotation2d addedAngle = new Rotation2d();
    if (RobotContainer.driveStick.getRawButton(reverseButtonNumber)) {
      addedAngle = new Rotation2d(Math.PI);
    }
    double rawDifference = driveAngle.minus(this.goalAngle).plus(addedAngle).getRadians();
    return rawDifference;
  }

  private double getSpeedOutput(double normDist, double rotationalDiff) {
    int multiplier = 1;
    if (RobotContainer.driveStick.getRawButton(reverseButtonNumber)) {
      multiplier = -1;
    }
    double speedOutput = normDist * Math.cos(rotationalDiff) * multiplier;
    return speedOutput;
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
