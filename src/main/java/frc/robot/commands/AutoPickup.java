// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.subsystems.BallCamera;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.Constants;

/**
 * A command that drives the robot to the ball, and picks it up using the intake
 * 
 * Requires the drivetrain, intake, and the ball camera
 */
public class AutoPickup extends CommandBase {
  private Drivetrain driveTrain;
  private Intake intake;
  private BallCamera camera;
  private Trajectory trajectory;
  private ParallelDeadlineGroup commandGroup;

  /** Creates a new AutoPickup. */
  public AutoPickup(Drivetrain driveTrain, Intake intake, BallCamera camera) {
    this.driveTrain = driveTrain;
    this.intake = intake;
    this.camera = camera;

    this.trajectory = null;
    this.commandGroup = null;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double startVelocity = Constants.kDriveKinematics.toChassisSpeeds(this.driveTrain.getWheelSpeeds()).vxMetersPerSecond;

    this.trajectory = this.camera.getTrajectory(startVelocity);
    if (this.trajectory == null) {
      // No ball to pick up, so there's nowhere to move, so just return
      // isFinished will see that there's no trajectory and end this command
      return;
    }

    this.commandGroup = new ParallelDeadlineGroup(  // We only want to take in balls while we're moving
      new FollowTrajectory(this.driveTrain, this.trajectory, true),
      new RunIntake(this.intake, Constants.intakeButtonSpeed)
    );
    this.commandGroup.schedule();
  }


  @Override
  public boolean isFinished() {
    if (this.trajectory == null || this.commandGroup == null) {
      return true;
    }
    return this.commandGroup.isFinished();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (interrupted && this.commandGroup != null) {
      this.commandGroup.cancel();
    }
  }
}
