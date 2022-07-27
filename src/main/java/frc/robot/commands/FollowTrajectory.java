// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class FollowTrajectory extends CommandBase {
  /** Creates a new FollowTrajectory. */
  private Trajectory trajectory;
  private Command ramseteCommand;

  public FollowTrajectory(Drivetrain drivetrain, Trajectory trajectory, boolean stopWhenFinished) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.trajectory = trajectory.transformBy(drivetrain.getPose().minus(new Pose2d()));

    this.ramseteCommand = new RamseteCommand(
        this.trajectory,
        drivetrain::getPose,
        new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(Constants.ksVolts, Constants.kvVoltSecondsPerMeter,
            Constants.kaVoltSecondsSquaredPerMeter),
        Constants.kDriveKinematics,
        drivetrain::getWheelSpeeds,
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),
        drivetrain::tankDriveVolts,
        drivetrain);

    if (stopWhenFinished) {
      this.ramseteCommand = this.ramseteCommand.andThen(() -> drivetrain.tankDriveVolts(0, 0));
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ramseteCommand.schedule();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ramseteCommand.cancel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.ramseteCommand.isFinished();
  }
}
