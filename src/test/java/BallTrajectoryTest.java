import org.junit.jupiter.api.*;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import static org.junit.jupiter.api.Assertions.*;
import frc.robot.subsystems.*;


public class BallTrajectoryTest {

    @Test
    public void nonNullInstanceCheck() {
        var trajectoryToBall = new BallCamera().new BallCameraTrajectory(new Translation2d(3, 1), 0).getTrajectory();
        assertNotNull(trajectoryToBall);
    }

    @Test
    public void nullInstanceCheck() {
        var trajectoryToBall = new BallCamera().new BallCameraTrajectory(null, 0).getTrajectory();
        assertNull(trajectoryToBall);
    }

    @Test
    public void finalPoseCheck() {
        var ballTranslation = new Translation2d(3, -6);
        var expectedEndPose = new Pose2d(ballTranslation, new Rotation2d(ballTranslation.getX(), ballTranslation.getY()));
        var actualEndPose = new BallCamera().new BallCameraTrajectory(ballTranslation, 0).getEndPose();
        assertEquals(expectedEndPose, actualEndPose);
    }
}
