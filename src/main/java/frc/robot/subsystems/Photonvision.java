package frc.robot.subsystems;

import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.PhotonCamera;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Photonvision extends SubsystemBase {
    // Create an instance of the PhotonCamera
    PhotonCamera camera = new PhotonCamera("limelight");
    PhotonTrackedTarget target;

    // Variables to store the target data
    double Yaw;
    double Area;
    double Pitch;
    double ID;

    @Override
    public void periodic() {
        // Get the latest result from the camera
        PhotonPipelineResult result = camera.getLatestResult();
        // Check if there are any targets detected
        if (result.hasTargets()) {
            // Retrieve the best target from the result
            target = result.getBestTarget();

            // Extract the data from the target
            Yaw = target.getYaw();
            Area = target.getArea();
            Pitch = target.getPitch();//suGay
            ID = target.getFiducialId();

            // Display the target data on the SmartDashboard
            SmartDashboard.putNumber("Tag_Yaw", Yaw);
            SmartDashboard.putNumber("Tag_Area", Area);
            SmartDashboard.putNumber("Tag_Pitch", Pitch);
            SmartDashboard.putNumber("Tag_ID", target.getFiducialId());
        }
    }
}


