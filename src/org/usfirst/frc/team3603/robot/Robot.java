package org.usfirst.frc.team3603.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	AHRS navx = new AHRS(SerialPort.Port.kMXP);
	MyEncoder enc = new MyEncoder(0);
	
	CANTalon frontLeft = new CANTalon(0);
	CANTalon backLeft = new CANTalon(1);
	CANTalon frontRight = new CANTalon(2);
	CANTalon backRight = new CANTalon(3);
	RobotDrive mainDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	
	Joystick joy1 = new Joystick(0);
	
	@Override
	public void robotInit() {
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}
	
	@Override
	public void teleopPeriodic() {
	}
	
	@Override
	public void testPeriodic() {
	}
	
	public void read() {
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
	}
}

