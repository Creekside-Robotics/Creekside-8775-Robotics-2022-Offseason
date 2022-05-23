// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TestAutoDrive extends SequentialCommandGroup {
  private Drivetrain drivetrain;
  /** Creates a new TestAutoDrive. */
  public TestAutoDrive(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new KinematicAutoDrive(this.drivetrain, false, true, 1, 0),
      new KinematicAutoDrive(this.drivetrain, true, true, 0, 180),
      new KinematicAutoDrive(this.drivetrain, true, true, 1, 0),
      new KinematicAutoDrive(this.drivetrain, true, true, 0, -180)
    );
  }
}
