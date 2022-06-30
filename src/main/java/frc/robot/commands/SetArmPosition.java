// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmComponent;

// Sets arm to inputted position
public class SetArmPosition extends CommandBase {
  /** Creates a new SetArmPosition. */
  private ArmComponent arm;
  private boolean hold;
  private double endPosition;
  final private double speed = 0.8;
  final private double tolerance = 0.03;

  public SetArmPosition(ArmComponent arm, double position, boolean hold) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.arm = arm;
    this.hold = hold;
    this.endPosition = position;
    addRequirements(this.arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double difference = this.endPosition - this.arm.getPosition();
    if (Math.abs(difference) < tolerance) {
      this.arm.setSpeed(0);
    } else if (difference > 0) {
      this.arm.setSpeed(this.speed);
    } else if (difference < 0) {
      this.arm.setSpeed(-this.speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(this.endPosition - this.arm.getPosition()) < tolerance && !this.hold;
  }
}
