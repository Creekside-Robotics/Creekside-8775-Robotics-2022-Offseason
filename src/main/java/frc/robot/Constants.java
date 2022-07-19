// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // CAN IDs
    public static int drivetrain1 = 1;
    public static int drivetrain2 = 2;
    public static int drivetrain3 = 3;
    public static int drivetrain4 = 4;
    public static int flywheel = 5;
    public static int frontConveyer = 8;
    public static int backConveyer = 9;
    public static int redArmId = 6;
    public static int yellowArmId = 7;

    // Physical Encoder Constants
    public static double redRevInRan = 3.1666;
    public static int tiltId = 10;
    public static double tiltRevInRan = 220;
    public static double tickPerRev = 1500;
    public static double yellowRevInRan = 5.5;

    // DIO Constants
    public static int leftEncoder1 = 4;
    public static int leftEncoder2 = 5;
    public static int rightEncoder1 = 6;
    public static int rightEncoder2 = 7;

    // Drivetrain Physical Constants
    public static double drivetrainDistancePerPulse = 0.000233771777; // Based on CAD values, will need to be verified.

    public static final double ksVolts = 0.22;
    public static final double kvVoltSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;

    public static final double kPDriveVel = 8.5;

    public static final double kTrackwidthMeters = 0.69;
    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;

    // Port Numbers
    public static int driveStickPortNumber = 0;
    public static int climbStickPortNumber = 1;

    // Shooting mechanism speed
    public static int defaultShooterSpeed = 0;
    public static double shootTriggerSpeed = 9;

    // Intake speed
    public static int defaultIntakeSpeed = 0;
    public static double intakeButtonSpeed = 0.5;

    //Default Axis For Arms
    public static String defaultRedArmPosition = "0";
    public static String defaultYellowArmPosition = "0";
    public static String defaultTiltArmPosition = "0";
    // Axis' for arms

    public static String redArmAxis = "Y";
    public static String yellowArmAxis = "X";
    public static String tiltArmAxis = "Z";
}
