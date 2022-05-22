// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootBalls extends ParallelDeadlineGroup {
  /** Creates a new ShootBalls. */
  public ShootBalls(Shooter shootSub, Intake intakeSub, int rpm) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(new IntakeShootingMovement(intakeSub));
    addCommands(new RunFlywheel(shootSub, 10000, rpm));
    // addCommands(new FooCommand(), new BarCommand());
  }
}
