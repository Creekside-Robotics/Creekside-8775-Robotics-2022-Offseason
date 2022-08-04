// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveToPose extends CommandBase {
  /** Creates a new MoveToPose. */

  private Drivetrain drivetrain;
  private Pose2d pose;

  public MoveToPose(Drivetrain drivetrain, Pose2d pose) {
    this.drivetrain = drivetrain;
    this.pose = pose;
    addRequirements(this.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  private Trajectory getTrajectoryToPose() {
    var pose = this.drivetrain.getPose();
    //This is where we left off, resume here
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
