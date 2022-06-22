// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;

// Class for arm using SIM motor and through bore encoder
public class SIMArm extends ArmComponent {
  /** Creates a new SIMArm. */
  private Encoder encoder;

  public SIMArm(int devID, boolean reverse, boolean reverseEncoder, double encoderMultiplier, int encoderPort1, int encoderPort2, String key) {
    super(devID, MotorType.kBrushed, reverse, key);
    this.encoder = new Encoder(encoderPort1, encoderPort2);
    this.encoder.setReverseDirection(reverseEncoder);
    this.encoder.setDistancePerPulse(1 / (encoderMultiplier * Constants.tickPerRev));
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
