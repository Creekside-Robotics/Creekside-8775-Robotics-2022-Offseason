// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.ArrayList;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class MoveToPose extends CommandBase {
  /** Creates a new MoveToPose. */

  private Drivetrain drivetrain;
  private Pose2d endingPose;
  private Command command;
  private ArrayList<Translation2d> interiorWaypoints;
  private double endVelocity;

  public MoveToPose(Drivetrain drivetrain, Pose2d endingPose, ArrayList<Translation2d> interiorWaypoints, double endVelocity) {
    this.drivetrain = drivetrain;
    this.endingPose = endingPose;
    this.interiorWaypoints = interiorWaypoints;
    this.endVelocity = endVelocity;
    addRequirements(this.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    var trajectoryToPose = getTrajectoryToPose();
    this.command = new FollowTrajectory(this.drivetrain, trajectoryToPose, false);
    this.command.schedule();
  }

  private Trajectory getTrajectoryToPose() {
    var initial = this.drivetrain.getPose();
    var config = new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond, Constants.kMaxAccelerationMetersPerSecondSquared);
    config.setStartVelocity(Constants.kDriveKinematics.toChassisSpeeds(this.drivetrain.getWheelSpeeds()).vxMetersPerSecond);
    config.setEndVelocity(this.endVelocity);
    //more code may be needed later for reversing
    return TrajectoryGenerator.generateTrajectory(initial, this.interiorWaypoints, this.endingPose, config).relativeTo(initial); //Converting field relative pose to robot relative pose.
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.command.cancel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.command.isFinished();
  }
}
