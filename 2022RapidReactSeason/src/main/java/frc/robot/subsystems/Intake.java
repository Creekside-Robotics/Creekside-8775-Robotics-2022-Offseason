package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Sensor data management will be added once we recive the pigeon order.
public class Intake extends SubsystemBase {
  //Robot objects
  private final CANSparkMax backConveyerMotor;
  private final CANSparkMax frontConveyerMotor;
  private double multiplier;
  
  //Sensor input varibles

  public Intake(double multiplier) {
    this.backConveyerMotor = new CANSparkMax(Constants.backConveyer, MotorType.kBrushed);
    this.frontConveyerMotor = new CANSparkMax(Constants.frontConveyer, MotorType.kBrushed);
    this.multiplier = multiplier;
  }

  public void setIntakeSpeed(double speed){
    this.backConveyerMotor.set(speed * this.multiplier);
    this.frontConveyerMotor.set(speed * this.multiplier);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}