
package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Encoder;
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

  // Automation objects
  private Encoder leftEncoder;
  private Encoder rightEncoder;
  private Pigeon2 pigeon;
  private DifferentialDriveOdometry odometry;


  public Drivetrain() {
    this.myVictor1 = new WPI_VictorSPX(Constants.drivetrain1);
    this.myVictor2 = new WPI_VictorSPX(Constants.drivetrain2);
    this.myVictor3 = new WPI_VictorSPX(Constants.drivetrain3);
    this.myVictor4 = new WPI_VictorSPX(Constants.drivetrain4);
    this.groupleft = new MotorControllerGroup(myVictor1, myVictor2);
    this.groupright = new MotorControllerGroup(myVictor3, myVictor4);
    this.groupright.setInverted(true);
    this.robotDrive = new DifferentialDrive(groupright, groupleft);

    this.leftEncoder = new Encoder(Constants.leftEncoder1, Constants.leftEncoder2, false);
    this.leftEncoder.setDistancePerPulse(Constants.drivetrainDistancePerPulse);

    this.rightEncoder = new Encoder(Constants.rightEncoder1, Constants.rightEncoder2, false);
    this.rightEncoder.setDistancePerPulse(Constants.drivetrainDistancePerPulse);

    this.pigeon = new Pigeon2(0);

    this.odometry = new DifferentialDriveOdometry(new Rotation2d(0));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    this.odometry.update(getRotation(), this.leftEncoder.getDistance(), this.rightEncoder.getDistance());
  }

  public void setMovement(double speed, double turnRate){
    this.robotDrive.arcadeDrive(speed, -turnRate);
  }

  public Pose2d getPose() {
    return this.odometry.getPoseMeters();
  }

  public Rotation2d getRotation(){
    return Rotation2d.fromDegrees(this.pigeon.getYaw());
  }

  public void tankDriveVolts(double leftVolts, double rightVolts){
    this.groupleft.setVoltage(leftVolts);
    this.groupright.setVoltage(rightVolts);
    this.robotDrive.feed();
  }

  public DifferentialDriveWheelSpeeds wheelSpeeds(){
    return new DifferentialDriveWheelSpeeds(this.leftEncoder.getRate(), this.rightEncoder.getRate());
  }

  public void resetPose(){
    this.leftEncoder.reset();
    this.rightEncoder.reset();
    this.pigeon.setYaw(0);
    this.odometry.resetPosition(new Pose2d(), getRotation());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
