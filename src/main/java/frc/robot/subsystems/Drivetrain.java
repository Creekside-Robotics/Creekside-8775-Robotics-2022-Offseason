
package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Drivetrain, odometry, and drive commands will be added soon.
public class Drivetrain extends SubsystemBase {
  //Robot objects
  private final WPI_VictorSPX myVictor1;
  private final WPI_VictorSPX myVictor2;
  private final WPI_VictorSPX myVictor3;
  private final WPI_VictorSPX myVictor4;
  private final MotorControllerGroup groupleft;
  private final MotorControllerGroup groupright;
  private DifferentialDrive robotDrive;

  public Drivetrain() {
    this.myVictor1 = new WPI_VictorSPX(Constants.drivetrain1);
    this.myVictor2 = new WPI_VictorSPX(Constants.drivetrain2);
    this.myVictor3 = new WPI_VictorSPX(Constants.drivetrain3);
    this.myVictor4 = new WPI_VictorSPX(Constants.drivetrain4);
    this.groupleft = new MotorControllerGroup(myVictor1, myVictor2);
    this.groupright = new MotorControllerGroup(myVictor3, myVictor4);
    this.groupright.setInverted(true);
    this.robotDrive = new DifferentialDrive(groupright, groupleft);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMovement(double speed, double turnRate){
    this.robotDrive.arcadeDrive(speed, -turnRate);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
