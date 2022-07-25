package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Base class of camera subsystem
public abstract class AbstractCamera extends SubsystemBase {
    protected PhotonCamera camera;
    protected int pipelineIndex;
    protected double cameraHeight;
    protected double cameraPitch;
    protected double targetHeight;
    protected Translation2d cameraOffset;

    /**
     * Constructs the camera
     *
     * @param cameraName The nickname of the camera.
     * @param pipelineIndex The index of the pipeline
     * @param cameraHeight The height of the camera above the ground, in meters
     * @param cameraPitch The pitch of the camera above the ground, in degrees
     * @param cameraOffset The offset of the camera from the center of the robot as a translation, in meters
     * @param targetHeight The height of the target, in meters
     */    
    public AbstractCamera(String cameraName, int pipelineIndex, double cameraHeight, double cameraPitch, Translation2d cameraOffset, double targetHeight) {
        this.camera = new PhotonCamera(cameraName);
        this.camera.setPipelineIndex(pipelineIndex);

        this.cameraHeight = cameraHeight;
        this.cameraPitch = cameraPitch;
        this.cameraOffset = cameraOffset;
        this.targetHeight = targetHeight;
    }

    /**
     * Returns whether the camera has targets.
     *
     * @return If the camera has targets.
     */
    public boolean hasTargets() {
        return this.camera.getLatestResult().hasTargets();
    }

    /**
     * Finds the camera's best target
     */
    public PhotonTrackedTarget getBestTarget() {
        var result = this.camera.getLatestResult();
        return result.getBestTarget();
    }

    /**
     * Gets the distance between the camera and the target
     * 
     * @param target The target to get the distance from
     * @return The distance between the camera and the target, in meters
    */
    public double getDistance(PhotonTrackedTarget target) {
        return PhotonUtils.calculateDistanceToTargetMeters(this.cameraHeight, this.targetHeight, this.cameraPitch, target.getPitch());
    }

    /**
     * Finds the translation to the camera's current target
     * 
     * @return The translation to the camera's best target
     */
    public Translation2d getRelativeTranslation() {
        var target = this.getBestTarget();
        if (target == null) {
            return null;
        }
        double distance = this.getDistance(target);

        Translation2d translation = PhotonUtils.estimateCameraToTargetTranslation(
            distance, Rotation2d.fromDegrees(-target.getYaw())
        );
        translation.plus(this.cameraOffset);
        return translation;
    }

    public abstract Trajectory getTrajectory();

}

