// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GoalCamera;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoShoot extends CommandBase {

  private Drivetrain drivetrain;
  private GoalCamera goalCamera;
  private Intake intake;
  private Shooter shooter;
  private Command shoot;
  private Trajectory finalTrajectory;

  public AutoShoot(Drivetrain drivetrain, Intake intake, Shooter shooter, GoalCamera goalcamera) {
    //defining classes
    this.drivetrain = drivetrain;
    this.goalCamera = goalcamera;
    this.intake = intake;
    this.shooter = shooter;
  }


  // Called just before this Command runs the first time
  @Override
  public void initialize() {

    double robotVelocity =  Constants.kDriveKinematics.toChassisSpeeds(this.drivetrain.getWheelSpeeds()).vxMetersPerSecond;
    this.finalTrajectory = this.goalCamera.getTrajectory(robotVelocity);

    if (finalTrajectory != null){

      Command follow = new FollowTrajectory(drivetrain, finalTrajectory, true);

      //keeps the drivetrain from moving
      Command lock = new SetDriveMovement(drivetrain, 0.0, 0.0);

      Command lockedWhileShooting = new ParallelDeadlineGroup(new ManualShoot(this.shooter, this.intake), lock);


      //Running everything in a sequential command group

      this.shoot = new SequentialCommandGroup(follow, lockedWhileShooting);
      
      this.shoot.schedule();
    }  
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return this.shoot == null || this.finalTrajectory == null || this.shoot.isFinished();
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    if (this.shoot != null){
      this.shoot.cancel();
    }
    
  }
}