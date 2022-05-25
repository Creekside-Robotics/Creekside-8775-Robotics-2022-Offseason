// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ExtendedIntake extends SubsystemBase {
  /** Creates a new ExtendedIntake. */
  private DoubleSolenoid leftExtender;
  private DoubleSolenoid rightExtender;

  public ExtendedIntake() {
    this.leftExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.extenderLeftForwardChannel, Constants.extenderLeftBackwardChannel);
    this.rightExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.extenderRightForwardChannel, Constants.extenderRightBackwardChannel);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setForward(){
    this.leftExtender.set(Value.kForward);
    this.rightExtender.set(Value.kForward);
  }

  public void setReverse(){
    this.leftExtender.set(Value.kReverse);
    this.rightExtender.set(Value.kReverse);
  }
}
