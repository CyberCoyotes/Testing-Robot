package org.usfirst.frc.team3603.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	Victor backLeft = new Victor(1);
	Victor backRight = new Victor(2);
	Victor frontLeft = new Victor(3);
	Victor frontRight = new Victor(4);
	
	RobotDrive mainDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	
	Joystick joy1 = new Joystick(0);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	PIDController gyroCont = new PIDController(0.03, 0, 0, (PIDSource) gyro, (PIDOutput) mainDrive);
	
	Vision vision = new Vision();
	
	Lidar lidar;
	
	@Override
	public void robotInit() {
		backRight.setInverted(true);
    	frontRight.setInverted(true);
    	
    	mainDrive.setSafetyEnabled(false);
    	lidar = new Lidar(I2C.Port.kMXP);
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}
	
	@Override
	public void teleopPeriodic() {
		if(joy1.getRawButton(1)) {
			gyroCont.setSetpoint(vision.getAngle() + gyro.getAngle());
		}
		if(joy1.getRawButton(2)) {
			mainDrive.mecanumDrive_Cartesian(0, 0, gyroCont.get(), 0);
		}
		if(joy1.getRawButton(3)) {
			gyroCont.enable();
		}
		read();
	}
	
	@Override
	public void testPeriodic() {
	}
	
	public void read() {
		SmartDashboard.putNumber("PIDOutput1", gyroCont.get());
		SmartDashboard.putNumber("Lidar Distance", lidar.getDistance());
		SmartDashboard.putBoolean("Succes", lidar.success);
		SmartDashboard.putBoolean("Success2", lidar.success2);
	}
}

