// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmComponent extends SubsystemBase {
  /** Creates a new ArmComponent. */
  private CANSparkMax mainMotor;
  private double multiplier;
  private RelativeEncoder encoder;

  //Sensor input
  public double position;


  public ArmComponent(int deviceId, MotorType type, double multiplier, double revolutionsInRange) {
    this.mainMotor = new CANSparkMax(deviceId, type);
    this.multiplier = multiplier;
    this.encoder = this.mainMotor.getAlternateEncoder(8192);
    this.encoder.setPositionConversionFactor(1/revolutionsInRange);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    this.position = this.encoder.getPosition();
  }

  public void setSpeed(double speed){
    this.mainMotor.set(speed * this.multiplier);
  }

  public void reset(){
    this.encoder.setPosition(0);
  }
}
