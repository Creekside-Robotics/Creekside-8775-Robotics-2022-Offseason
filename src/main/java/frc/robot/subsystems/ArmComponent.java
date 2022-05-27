// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmComponent extends SubsystemBase {
  /** Creates a new ArmComponent. */
  private CANSparkMax mainMotor;
  private double multiplier;
  private double encoderMultiplier;
  private Encoder encoder;

  //Sensor input
  public double position;


  public ArmComponent(int deviceId, MotorType type, double multiplier, double revolutionsInRange, double encoderMultiplier, int port1, int port2) {
    this.mainMotor = new CANSparkMax(deviceId, type);
    this.multiplier = multiplier;
    this.encoderMultiplier = encoderMultiplier;
    this.encoder = new Encoder(port1, port2);
    this.encoder.setDistancePerPulse(1/(Constants.tickPerRev * revolutionsInRange));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    this.position = this.encoder.getDistance() * this.encoderMultiplier;
  }

  public void setSpeed(double speed){
    this.mainMotor.set(speed * this.multiplier);
  }

  public void reset(){
    this.encoder.reset();;
  }
}
