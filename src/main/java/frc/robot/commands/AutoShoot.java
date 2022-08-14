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
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoShoot extends CommandBase {

  private Drivetrain drivetrain;
  private GoalCamera goalCamera;
  private Intake intake;
  private Shooter shooter;
  private Command shoot;

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
    Trajectory finalTrajectory = this.goalCamera.getTrajectory(robotVelocity);

    if (finalTrajectory != null){

      Command follow = new FollowTrajectory(drivetrain, finalTrajectory, true);

      //keeps the drivetrain from moving
      Command lock = new SetDriveMovement(drivetrain, 0.0, 0.0);

      //creates runFlywheel and runIntake commands so that they can be used in timedCommand
      Command runFlywheel = new RunFlywheel(shooter, Constants.ksVolts);
      Command runIntake =  new RunIntake(this.intake, Constants.defaultIntakeSpeed);

      Command firstIntake = new ParallelCommandGroup(new TimedCommand(runIntake, 0.5), lock); //Moves intake down for half a second while locking drivetrain

      Command firstFlywheel = new ParallelCommandGroup(new TimedCommand(runFlywheel, 2.0), lock); //Runs flywheel for 2 seconds while locking drivetrain
      
      //Runs both the flywheel and the intake for 2 seconds while locking down the drivetrain
      Command simultaneous = new TimedCommand(new ParallelCommandGroup(runIntake,runFlywheel,lock), 2.0);


      //Running everything in a sequential command group

      this.shoot = new SequentialCommandGroup(follow, firstIntake, firstFlywheel, simultaneous);
      
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
    double robotVelocity =  Constants.kDriveKinematics.toChassisSpeeds(this.drivetrain.getWheelSpeeds()).vxMetersPerSecond;
    Trajectory finalTrajectory = this.goalCamera.getTrajectory(robotVelocity);

    if (this.shoot == null || finalTrajectory == null){
      return true;
    }
    else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    this.shoot.cancel();
  }


}

