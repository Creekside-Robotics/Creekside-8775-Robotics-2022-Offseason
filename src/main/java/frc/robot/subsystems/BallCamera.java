package frc.robot.subsystems;

import java.util.ArrayList;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Constants;

public abstract class BallCamera extends AbstractCamera {
    //placeholder values for now
    private static double ballCameraHeight = 1;
    private static double ballCameraPitch = 1;
    private static Translation2d ballCameraOffset = new Translation2d();
    private static double ballTargetHeight = 1;

    private static DriverStation.Alliance alliance = DriverStation.getAlliance();
    private static int ballPipelineIndex = 0;

    public BallCamera() {
        super("BallCamera", ballPipelineIndex, ballCameraHeight, ballCameraPitch, ballCameraOffset, ballTargetHeight);

        if (alliance == DriverStation.Alliance.Red) {
            this.pipelineIndex = 0;
        } else {
            this.pipelineIndex = 1;
        }

        this.cameraHeight = ballCameraHeight;
        this.cameraPitch = ballCameraPitch;
        this.cameraOffset = ballCameraOffset;
        this.targetHeight = ballTargetHeight;
    }

    public Trajectory getTrajectory() {
        Pose2d initial = new Pose2d(); //origin

        Rotation2d endRotation = new Rotation2d(this.getRelativeTranslation().getX(), this.getRelativeTranslation().getY());
        Pose2d end = new Pose2d(this.getRelativeTranslation(), endRotation); //translation of ball

        ArrayList<Translation2d> interiorWaypoints = new ArrayList<Translation2d>();
 
        TrajectoryConfig config = new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond, Constants.kMaxAccelerationMetersPerSecondSquared);

        return TrajectoryGenerator.generateTrajectory(initial, interiorWaypoints, end, config);
    };
}
