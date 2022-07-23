import frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class TargetCamera extends AbstractCamera {
    
    public TargetCamera(){
        
        // Calling to the parent class to define variables
        double cameraHeight = super.cameraHeight;
        double cameraPitch = super.camerPitch;
        double cameraOffset = super.cameraOffset;
        double target_height = super.targetHeight;
        

    }
}



