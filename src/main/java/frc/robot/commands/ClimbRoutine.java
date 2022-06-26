// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmComponent;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ClimbRoutine extends SequentialCommandGroup {
  /** Creates a new ClimbRoutine. */
  public ClimbRoutine(ArmComponent redArm, ArmComponent yellowArm, ArmComponent tiltArm) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new ParallelCommandGroup(
      new SetArmPosition(redArm, 0, false),
      new SetArmPosition(yellowArm, 1, false),
      new SetArmPosition(tiltArm, 0, false)
    ),
    new ParallelDeadlineGroup(
      new SetArmPosition(tiltArm, 0.35, false),
      new SetArmPosition(redArm, 0, true),
      new SetArmPosition(yellowArm, 1, true)
    ),
    new ParallelCommandGroup(
      new SetArmPosition(yellowArm, 0, false),
      new SetArmPosition(tiltArm, 0.9, false),
      new SetArmPosition(redArm, 0, false)
    ),
    new ParallelDeadlineGroup(
      new SetArmPosition(redArm, 0.7, false),
      new SetArmPosition(yellowArm, 0, true),
      new SetArmPosition(tiltArm, 0.9, true) 
    ),
    new ParallelDeadlineGroup(
      new SetArmPosition(tiltArm, 0.5, false),
      new SetArmPosition(redArm, 0.8, true),
      new SetArmPosition(yellowArm, 0, true)
    ),
    new ParallelCommandGroup(
      new SetArmPosition(tiltArm, 0.5, false),
      new SetArmPosition(redArm, 0, false),
      new SetArmPosition(yellowArm, 1, false)
    ),
    new ParallelDeadlineGroup(
      new SetArmPosition(tiltArm, 0.1, false),
      new SetArmPosition(redArm, 0, true),
      new SetArmPosition(yellowArm, 1, true)
    ),
    new ParallelCommandGroup(
      new SetArmPosition(tiltArm, 0.1, false),
      new SetArmPosition(redArm, 0, false),
      new SetArmPosition(yellowArm, 0, false)
    ));
  }
}
