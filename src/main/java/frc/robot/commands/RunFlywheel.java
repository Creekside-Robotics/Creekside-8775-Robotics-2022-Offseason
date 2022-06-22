package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** Runs flywheel at set speed */
public class RunFlywheel extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter shooterSubsystem;
  private double flywheelSpeed;

  public RunFlywheel(Shooter subsystem, double speed) {
    this.shooterSubsystem = subsystem;
    this.flywheelSpeed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.shooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.shooterSubsystem.setSpeed(this.flywheelSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
