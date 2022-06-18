// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmComponent;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
// Test rouitine for arm
public class TestArmMovement extends SequentialCommandGroup {
  /** Creates a new TestArmMovement. */
  private ArmComponent arm;
  public TestArmMovement(ArmComponent arm) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    this.arm = arm;
    addCommands(
      new SetArmPosition(this.arm, 0, false),
      new SetArmPosition(this.arm, 0.5, false),
      new SetArmPosition(this.arm, 0.25, false),
      new SetArmPosition(this.arm, 0.75, false),
      new SetArmPosition(this.arm, 0.5, false),
      new SetArmPosition(this.arm, 1.0, false),
      new SetArmPosition(this.arm, 0, false)
    );
  }
}
