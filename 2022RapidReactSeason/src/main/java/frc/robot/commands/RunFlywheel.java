package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class RunFlywheel extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter shooterSubsystem;
  private double stopTime;
  private int RPM;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RunFlywheel(Shooter subsystem, double maxTime, int rpm) {
    this.stopTime = System.currentTimeMillis() + maxTime;
    this.shooterSubsystem = subsystem;
    this.RPM = rpm;
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
    this.shooterSubsystem.setRPM(this.RPM);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return System.currentTimeMillis() > this.stopTime;
  }
}
