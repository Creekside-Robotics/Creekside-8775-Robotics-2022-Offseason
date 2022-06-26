// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

// Runs a command for a specified time or until command ends.
public class TimedCommand extends CommandBase {
  /** Creates a new TimedCommand. */
  private Command command;
  private double timeout;
  private Timer timer;

  public TimedCommand(Command command, double timeout) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.command = command;
    this.timeout = timeout;


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    command.schedule();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    command.cancel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > timeout || command.isFinished();
  }
}
