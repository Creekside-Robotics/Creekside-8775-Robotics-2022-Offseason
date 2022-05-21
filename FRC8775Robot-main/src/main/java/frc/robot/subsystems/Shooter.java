package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Sensor data management will be added once we recive the pigeon order.
public class Shooter extends SubsystemBase {
  //Robot objects
  private CANSparkMax flywheelMotor;
  private RelativeEncoder flywheelEncoder;

  //Sensor input varibles
  public double actualFlywheelRPM = 0;

  public Shooter() {
    this.flywheelMotor = new CANSparkMax(Constants.flywheel, MotorType.kBrushless);
    this.flywheelEncoder = this.flywheelMotor.getEncoder();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    this.actualFlywheelRPM = this.flywheelEncoder.getVelocity();
  }

  public void setRPM(int rpm){
    this.flywheelMotor.setVoltage(rpm / Constants.revNeoKv);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}