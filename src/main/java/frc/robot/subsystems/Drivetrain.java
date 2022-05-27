
package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Sensor data management will be added once we recive the pigeon order.
public class Drivetrain extends SubsystemBase {
  //Robot objects
  private final WPI_VictorSPX myVictor1;
  private final WPI_VictorSPX myVictor2;
  private final WPI_VictorSPX myVictor3;
  private final WPI_VictorSPX myVictor4;
  private final MotorControllerGroup groupleft;
  private final MotorControllerGroup groupright;
  private DifferentialDrive robotDrive;
  private Pigeon2 pigeon2;

  //Sensor input varibles
  private double previousTime = System.currentTimeMillis() / 1000;
  public double acceleration = 0;
  public double velocity = 0;
  public double displacement = 0;
  public double yaw = 0;

  public Drivetrain() {
    this.myVictor1 = new WPI_VictorSPX(Constants.drivetrain1);
    this.myVictor2 = new WPI_VictorSPX(Constants.drivetrain2);
    this.myVictor3 = new WPI_VictorSPX(Constants.drivetrain3);
    this.myVictor4 = new WPI_VictorSPX(Constants.drivetrain4);
    this.groupleft = new MotorControllerGroup(myVictor1, myVictor2);
    this.groupright = new MotorControllerGroup(myVictor3, myVictor4);
    this.groupright.setInverted(true);
    this.robotDrive = new DifferentialDrive(groupright, groupleft);
    this.pigeon2 = new Pigeon2(Constants.pigeonId);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double timeDiff = System.currentTimeMillis() / 1000 - this.previousTime;
    this.previousTime = System.currentTimeMillis() / 1000;
    short[] accelerationArray = new short[3];
    this.pigeon2.getBiasedAccelerometer(accelerationArray);
    this.acceleration = accelerationArray[1] / 16384 * 9.8;
    this.velocity += this.acceleration * timeDiff;
    this.displacement += this.velocity * timeDiff;
    this.yaw = -this.pigeon2.getYaw();
  }

  public void setMovement(double speed, double turnRate){
    this.robotDrive.arcadeDrive(speed, -turnRate);
  }

  public void setBrake(boolean mode){
    if(mode){
      this.myVictor1.setNeutralMode(NeutralMode.Brake);
      this.myVictor2.setNeutralMode(NeutralMode.Brake);
      this.myVictor3.setNeutralMode(NeutralMode.Brake);
      this.myVictor4.setNeutralMode(NeutralMode.Brake);
    } else {
      this.myVictor1.setNeutralMode(NeutralMode.Coast);
      this.myVictor2.setNeutralMode(NeutralMode.Coast);
      this.myVictor3.setNeutralMode(NeutralMode.Coast);
      this.myVictor4.setNeutralMode(NeutralMode.Coast);
    }
  }

  public void resetKenematics(){
    this.velocity = 0;
    this.displacement = 0;
  }

  public void resetDisplacement(){
    this.displacement = 0;
  }

  public void resetYaw(){
    this.pigeon2.setYaw(0);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
