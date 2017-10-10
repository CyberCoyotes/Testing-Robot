package org.usfirst.frc.team3603.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	MyPID gy = new MyPID(0.05, 0.005, 0.0);
	
	Victor backLeft = new Victor(1);
	Victor backRight = new Victor(2);
	Victor frontLeft = new Victor(3);
	Victor frontRight = new Victor(4);
	RobotDrive mainDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	
	Joystick joy1 = new Joystick(0);
	Timer timer = new Timer();
	
	@Override
	public void robotInit() {
		timer.start();
		backRight.setInverted(true);
    	frontRight.setInverted(true);
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
			//gy.preD(timer.get(), gyro.getAngle());
			mainDrive.mecanumDrive_Cartesian(0, 0, gy.getSpeed(gyro.getAngle()), 0);
			SmartDashboard.putNumber("gy", gy.getSpeed(gyro.getAngle()));
			SmartDashboard.putNumber("gyro", gyro.getAngle());
		} else {
			//mainDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
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

