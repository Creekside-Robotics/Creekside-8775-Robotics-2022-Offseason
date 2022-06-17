// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;

public class SIMArm extends ArmComponent {
  /** Creates a new SIMArm. */
  private Encoder encoder;

  public SIMArm(int devID, double multiplier, double encoderMultiplier, int encoderPort1, int encoderPort2) {
    super(devID, MotorType.kBrushed, multiplier);
    this.encoder = new Encoder(encoderPort1, encoderPort2);
    this.encoder.setDistancePerPulse(encoderMultiplier);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void resetPosition() {
    this.encoder.reset();
    
  }

  @Override
  public double getPosition() {
    return this.encoder.getDistance();
  }
}
