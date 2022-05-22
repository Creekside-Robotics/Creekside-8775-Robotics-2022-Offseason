
package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class RunIntake extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake intakeSubsystem;
  private double stopTime;
  private double intakeSpeed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RunIntake(Intake subsystem, double maxTime, double intakeSpeed) {
    this.stopTime = System.currentTimeMillis() + maxTime;
    this.intakeSubsystem = subsystem;
    this.intakeSpeed = intakeSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.intakeSubsystem.setIntakeSpeed(this.intakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(System.currentTimeMillis() > this.stopTime) {
      return true;
    } else {
      return false;
    }
  }
}
