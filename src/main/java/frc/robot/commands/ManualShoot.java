// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
import frc.robot.commands.RunFlywheel;
import frc.robot.commands.RunIntake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ManualShoot extends SequentialCommandGroup {
  /** Creates a new ManualShoot. */
  public ManualShoot(Shooter shooter, Intake intake) {
    
    //creates runFlywheel and runIntake commands so that they can be used in timedCommand
    Command runFlywheel = new RunFlywheel(shooter, Constants.ksVolts);
    Command runIntakeIn =  new RunIntake(intake, -Constants.defaultIntakeSpeed);
    Command runIntakeOut = new RunIntake(intake, Constants.defaultIntakeSpeed);

    Command firstIntake = runIntakeIn.withTimeout(0.5); //Moves intake down for half a second while locking drivetrain

    Command firstFlywheel = runFlywheel.withTimeout(2.0); //Runs flywheel for 2 seconds while locking drivetrain

    Command simultaneous = new ParallelCommandGroup(runIntakeOut,runFlywheel).withTimeout(2.0);

    addCommands(firstIntake, firstFlywheel, simultaneous);
  }
}
