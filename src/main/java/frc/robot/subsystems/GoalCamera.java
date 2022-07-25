package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import frc.robot.Constants;

public class GoalCamera extends AbstractCamera{

    private static String goalCameraName = "goalCamera";
    private static int goalPipelineIndex = 0;
    //rest of the variables are to be determined
    private static double goalCameraHeight = 2.0;
    private static double goalCameraPitch = 2.0;
    private static Translation2d goalCameraOffset = new Translation2d(2.0,2.0);
    private static double goalHeight = 2.0; 
    private static double distanceTogoal = 2.0;


    public GoalCamera() {
        super(goalCameraName, goalPipelineIndex, goalCameraHeight, goalCameraPitch, goalCameraOffset, goalHeight);
        //TODO Auto-generated constructor stub
        
    }

    @Override
    public Trajectory getTrajectory() {
        // TODO Auto-generated method stub
        var goalTranslation = getRelativeTranslation();
        var robotPosition = new Pose2d();
        var finalAngle = new Rotation2d(goalTranslation.getX(),goalTranslation.getY());
        var unitVector = goalTranslation.div(goalTranslation.getNorm());
        var multipliedUnitVector = unitVector.times(distanceTogoal);
        var finalDisplacement = goalTranslation.minus(multipliedUnitVector);
        var finalPose = new Pose2d(finalDisplacement,finalAngle);
        var interiorWaypoints = new ArrayList<Translation2d>();
        var trajectoryConfig = new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,Constants.kMaxAccelerationMetersPerSecondSquared);
        var finalTrajectory = TrajectoryGenerator.generateTrajectory(robotPosition, interiorWaypoints, finalPose, trajectoryConfig);
        return finalTrajectory;
        

    }
    
}
