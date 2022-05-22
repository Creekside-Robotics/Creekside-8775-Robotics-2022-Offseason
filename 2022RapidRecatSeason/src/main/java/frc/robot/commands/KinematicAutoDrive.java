// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class KinematicAutoDrive extends CommandBase {
  /** Creates a new KenematicAutoDrive. */
  private PIDController rotationControl = new PIDController(1/90, 1/45, 0);
  private PIDController movementControl = new PIDController(0.3, 0.5, 0);
  private Drivetrain drivetrain;
  private boolean following;
  private boolean endWhenDone;
  private double goalPosition;
  private double goalRotation;

  public KinematicAutoDrive(Drivetrain drivetrain, boolean following, boolean endWhenDone, double goalPosition, double goalRotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.following = following;
    this.endWhenDone = endWhenDone;
    this.goalPosition = goalPosition;
    this.goalRotation = goalPosition;
    addRequirements(this.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.setBrake(true);
    drivetrain.resetYaw();
    this.rotationControl.setSetpoint(this.goalRotation);
    this.movementControl.setSetpoint(this.goalPosition);
    
    if(following) {
      drivetrain.resetDisplacement();
    } else {
      drivetrain.resetKenematics();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.setMovement(movementControl.calculate(drivetrain.displacement), rotationControl.calculate(drivetrain.yaw));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(movementControl.getPositionError()) < 0.1 && Math.abs(rotationControl.getPositionError()) < 10 && endWhenDone;
  }
}
