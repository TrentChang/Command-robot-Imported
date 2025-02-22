// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.text.html.HTML.Tag;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.chassisID;
import frc.robot.LimelightHelpers;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class chassis extends SubsystemBase {
  public final WPI_VictorSPX Frontleft = new WPI_VictorSPX(chassisID.FL);
  public final WPI_VictorSPX Frontright = new WPI_VictorSPX(chassisID.FR);
  public final WPI_VictorSPX RearLeft = new WPI_VictorSPX(chassisID.RL);
  public final WPI_VictorSPX Rearright = new WPI_VictorSPX(chassisID.RR);

  public double Tag_Area = 0;
  public double Tag_Yaw = 0;

  public DifferentialDrive tank = new DifferentialDrive(Frontleft, Frontright);
  
/** Creates a new ExampleSubsystem. */
  
  public chassis() {
    Frontleft.configFactoryDefault();
    Frontright.configFactoryDefault();
    RearLeft.configFactoryDefault();
    Rearright.configFactoryDefault();

    Frontleft.setInverted(false);
    Frontright.setInverted(false); 
    RearLeft.setInverted(false);
    Rearright.setInverted(false);

    RearLeft.follow(Frontleft);
    Rearright.follow(Frontright);
    }
  
  public void drive(double X, double Y){
    tank.arcadeDrive(X, -Y);
  }
  
  public void forward(){
    Frontleft.set(0.3);
    Frontright.set(0.3);
    RearLeft.set(0.3);
    Rearright.set(0.3);
    Frontleft.setInverted(false);
    Frontright.setInverted(true); 
    RearLeft.setInverted(false);
    Rearright.setInverted(true);
    
  }

  public void backward(){
    Frontleft.set(0.3);
    Frontright.set(0.3);
    RearLeft.set(0.3);
    Rearright.set(0.3);
    Frontleft.setInverted(true);
    Frontright.setInverted(false); 
    RearLeft.setInverted(true);
    Rearright.setInverted(false);
    
  }
  public void right(){
    Frontleft.set(0.3);
    Frontright.set(0.3);
    RearLeft.set(0.3);
    Rearright.set(0.3);
    Frontleft.setInverted(false);
    Frontright.setInverted(false); 
    RearLeft.setInverted(false);
    Rearright.setInverted(false);
    
  }
  public void left(){
    Frontleft.set(0.3);
    Frontright.set(0.3);
    RearLeft.set(0.3);
    Rearright.set(0.3);
    Frontleft.setInverted(true);
    Frontright.setInverted(true); 
    RearLeft.setInverted(true);
    Rearright.setInverted(true);
  }

  public void stop(){
    Frontleft.set(0);
    Frontright.set(0);
    RearLeft.set(0);
    Rearright.set(0);
  }

  public void autotarget() {
    double Tag_Area = SmartDashboard.getNumber("Tag_Area", 0);
    double Tag_Yaw = SmartDashboard.getNumber("Tag_Yaw", 0);

    if (Tag_Yaw < 9 && Tag_Yaw > -9 || Tag_Yaw == 0) {
      if (Tag_Area > 2 && Tag_Area < 6 || Tag_Area == 0) {
        stop();
      }
      else if (Tag_Area < 2 && Tag_Area > 0) {
        forward();
      }
      else if (Tag_Area > 6) {
        backward();
      }
    }
    else if (Tag_Yaw > 9) {
      right();
    }
    else if (Tag_Yaw < -9) {
      left();
    }
  }

  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
  }


  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
