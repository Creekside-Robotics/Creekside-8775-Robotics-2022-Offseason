// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmComponent;

public class SetArmPosition extends CommandBase {
  /** Creates a new SetArmPosition. */
  private ArmComponent arm;
  private boolean hold;
  private double endPosition;
  private PIDController armController;

  public SetArmPosition(ArmComponent arm, double position, boolean hold) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.armController = new PIDController(0.5, 0.5, 0);
    this.arm = arm;
    this.hold = hold;
    this.endPosition = position;
    addRequirements(this.arm);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.armController.setSetpoint(this.endPosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.arm.setSpeed(armController.calculate(this.arm.position));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(this.armController.getPositionError()) < 0.02 && !this.hold;
  }
}
