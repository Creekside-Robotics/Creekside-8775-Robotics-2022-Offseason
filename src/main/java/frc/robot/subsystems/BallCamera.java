package frc.robot.subsystems;

import java.util.ArrayList;

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
        this.camera.setPipelineIndex(this.pipelineIndex);
    }

    public Trajectory getTrajectory(double startVelocity) {
        return new BallCameraTrajectory(getRelativeTranslation(), startVelocity).getTrajectory();
    };

    private class BallCameraTrajectory{
        private Translation2d ballTranslation;
        private double robotVelocity;

        public BallCameraTrajectory(Translation2d ballTranslation, double robotVelocity) {
            this.ballTranslation = ballTranslation;
            this.robotVelocity = robotVelocity;
        }

        public Trajectory getTrajectory() {
            if (this.ballTranslation == null) {
                return null;
            }
            Pose2d startPose = getStartPose(); //Robot starts at the orgin
            ArrayList<Translation2d> interiorWaypoints = getInteriorWaypoints();
            Pose2d endPose = getEndPose();
            TrajectoryConfig config = getTrajectoryConfig();

            return TrajectoryGenerator.generateTrajectory(startPose, interiorWaypoints, endPose, config);
        }

        public Rotation2d getEndRotation() {
            return new Rotation2d(this.ballTranslation.getX(), this.ballTranslation.getY());
        }

        public TrajectoryConfig getTrajectoryConfig(){
            TrajectoryConfig config = new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond, Constants.kMaxAccelerationMetersPerSecondSquared);
            config.setStartVelocity(this.robotVelocity);
            return config;
        }

        public Pose2d getEndPose() {
            Rotation2d endRotation = getEndRotation();
            Translation2d endTranslation = this.ballTranslation;
            return new Pose2d(endTranslation, endRotation);
        }

        public Pose2d getStartPose() {
            return new Pose2d(); //Robot starts at the origin
        }

        public ArrayList<Translation2d> getInteriorWaypoints(){
            return new ArrayList<Translation2d>(); //No interior waypoints
        }
    }
}
