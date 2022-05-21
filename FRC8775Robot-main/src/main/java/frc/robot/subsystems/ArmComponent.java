// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmComponent extends SubsystemBase {
  /** Creates a new ArmComponent. */
  private CANSparkMax mainMotor;

  //Sensor input

  public ArmComponent(int deviceId) {
    this.mainMotor = new CANSparkMax(deviceId, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void setSpeed(double speed){
    this.mainMotor.set(speed);
  }
}
