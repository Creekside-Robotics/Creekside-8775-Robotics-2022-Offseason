// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int drivetrain1 = 1;
    public static int drivetrain2 = 2;
    public static int drivetrain3 = 3;
    public static int drivetrain4 = 4;
    public static int pigeonId = 0;

    public static int flywheel = 5;
    public static int revNeoKv = 300;

    public static int frontConveyer = 8;
    public static int backConveyer = 9;

    public static int redArmId = 6;
    public static double redRevInRan = 3.1666;
    public static int yellowArmId = 7;
    public static double yellowRevInRan = 5;
    public static int tiltId = 10;
    public static double tiltRevInRan = 12;

    public static int extenderLeftForwardChannel = 0;
    public static int extenderLeftBackwardChannel = 1;
    public static int extenderRightForwardChannel = 2;
    public static int extenderRightBackwardChannel = 3;
    public static double tickPerRev = 1200;
    
}
