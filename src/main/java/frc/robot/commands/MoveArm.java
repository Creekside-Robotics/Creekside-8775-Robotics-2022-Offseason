// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ArmComponent;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MoveArm extends CommandBase {
  /** Creates a new MoveArm. */
  private ArmComponent armSubsystem;
  private Joystick joy;
  private String axis;

  public MoveArm(ArmComponent subsystem, Joystick joystick, String axis) {
    this.armSubsystem = subsystem;
    this.joy = joystick;
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
        armSubsystem.setSpeed(joy.getX());
        break;
      case "Y":
        armSubsystem.setSpeed(-joy.getY());
        break;
      case "Z":
        armSubsystem.setSpeed(joy.getZ());
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
