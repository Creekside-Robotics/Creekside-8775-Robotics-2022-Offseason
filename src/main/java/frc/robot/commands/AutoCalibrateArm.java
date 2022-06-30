// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmComponent;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoCalibrateArm extends SequentialCommandGroup {
  /** Creates a new AutoCalibrateArm. */
  public AutoCalibrateArm(ArmComponent arm, double movementSpeed, double thresholdForStop, double offsetPosition) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new MoveArmUntilStall(arm, movementSpeed, thresholdForStop),
        new CalibrateArm(arm),
        new SetArmPosition(arm, offsetPosition, false),
        new CalibrateArm(arm));
  }
}
