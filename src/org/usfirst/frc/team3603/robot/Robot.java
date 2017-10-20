package org.usfirst.frc.team3603.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Robot extends IterativeRobot {
	
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	Victor backLeft = new Victor(1);
	Victor backRight = new Victor(2);
	Victor frontLeft = new Victor(3);
	Victor frontRight = new Victor(4);
	RobotDrive mainDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	PIDSource g = (PIDSource) gyro;
	PIDOutput m = (PIDOutput) mainDrive;
	PIDController gy = new PIDController(0, 0, 0, g, m);
	
	Joystick joy1 = new Joystick(0);
	Timer timer = new Timer();
	
	@Override
	public void robotInit() {
		timer.start();
		backRight.setInverted(true);
    	frontRight.setInverted(true);
    	
    	gy.setSetpoint(0);
    	gy.enable();
	}

	@Override
	public void autonomousInit() {
		
	}//

	@Override
	public void autonomousPeriodic() {
	}
	
	@Override
	public void teleopPeriodic() {
		if(joy1.getRawButton(1)) {
			mainDrive.mecanumDrive_Cartesian(0, 0, gy.get(), 0);
		}
	}
	
	@Override
	public void testPeriodic() {
	}
	
	public void read() {
		/*
		SmartDashboard.putNumber("getAngle", navx.getAngle());
		SmartDashboard.putNumber("getRate", navx.getRate());
		SmartDashboard.putNumber("getDisplacementX", navx.getDisplacementX());
		SmartDashboard.putNumber("getDisplacementY", navx.getDisplacementY());
		SmartDashboard.putNumber("getDisplacementZ", navx.getDisplacementZ());
		SmartDashboard.putNumber("getRawAccelX", navx.getRawAccelX());
		SmartDashboard.putNumber("getRawAccelY", navx.getRawAccelY());
		SmartDashboard.putNumber("getRawAccelZ", navx.getRawAccelZ());
		SmartDashboard.putNumber("getVelocityX", navx.getVelocityX());
		SmartDashboard.putNumber("getVeloctiyY", navx.getVelocityY());
		SmartDashboard.putNumber("getVelocityZ", navx.getVelocityZ());
		SmartDashboard.putBoolean("isRotating", navx.isRotating());
		SmartDashboard.putBoolean("isMoving", navx.isMoving());
		*/
	}
}

