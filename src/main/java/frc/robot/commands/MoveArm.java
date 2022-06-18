// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.ArmComponent;
import edu.wpi.first.wpilibj2.command.CommandBase;

// Used to move arms manually
public class MoveArm extends CommandBase {
  /** Creates a new MoveArm. */
  private ArmComponent armSubsystem;
  private String axis;

  public MoveArm(ArmComponent subsystem, String axis) {
    this.armSubsystem = subsystem;
    this.axis = axis;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (this.axis){
      case "X":
        armSubsystem.setSpeed(RobotContainer.driveStick.getX());
        break;
      case "Y":
        armSubsystem.setSpeed(-RobotContainer.driveStick.getY());
        break;
      case "Z":
        armSubsystem.setSpeed(RobotContainer.driveStick.getZ());
        break;
      default:
        armSubsystem.setSpeed(0);
        break;
    }
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
