// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class NeoArm extends ArmComponent {
  /** Creates a new NeoArm. */
  private RelativeEncoder encoder;
  
  public NeoArm(int devId, double multiplier, double encoderMultiplier) {
    super(devId, MotorType.kBrushless, multiplier);
    this.encoder = this.mainMotor.getEncoder();
    this.encoder.setPositionConversionFactor(encoderMultiplier);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void resetPosition() {
    this.encoder.setPosition(0);
  }

  @Override
  public double getPosition() {
    return this.encoder.getPosition();
  }
}
