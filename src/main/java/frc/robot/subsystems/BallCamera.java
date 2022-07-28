package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;

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
}
