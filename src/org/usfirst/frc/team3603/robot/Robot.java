package org.usfirst.frc.team3603.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	Victor backLeft = new Victor(1);
	Victor backRight = new Victor(2);
	Victor frontLeft = new Victor(3);
	Victor frontRight = new Victor(4);
	CANTalon talon = new CANTalon(1);
	
	RobotDrive mainDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	
	//Vision vision = new Vision();
	Joystick joy1 = new Joystick(0);
	//MyPID pid = new MyPID(0.5, 0.01, 0);
	
	Lidar lidar;
	
	@Override
	public void robotInit() {
		backRight.setInverted(true);
    	frontRight.setInverted(true);
    	
    	mainDrive.setSafetyEnabled(false);
    	lidar = new Lidar(I2C.Port.kMXP);
    	lidar.start();
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}
	
	@Override
	public void teleopPeriodic() {
		/*
		if(joy1.getRawButton(1)) {
			double rotation = pid.getPID(vision.getSpeed());
			mainDrive.mecanumDrive_Cartesian(0, 0, rotation, 0);
		} else if(Math.abs(joy1.getRawAxis(0)) >= 0.1 || Math.abs(joy1.getRawAxis(1)) >= 0.1){
			mainDrive.mecanumDrive_Cartesian(0, joy1.getRawAxis(1), joy1.getRawAxis(0), 0);
			pid.reset();
		} else {
			mainDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
			pid.reset();
		}
		*/
		read();
	}
	
	@Override
	public void testPeriodic() {
	}
	
	public void read() {
		/*
		SmartDashboard.putNumber("Vision speed", vision.getSpeed());
		SmartDashboard.putNumber("PID", pid.getPID(vision.getSpeed()));
		SmartDashboard.putString("Keys", vision.getKeys());
		SmartDashboard.putNumber("Derivitave", pid.derivitave);
		*/
		SmartDashboard.putNumber("Lidar Distance", lidar.getDistance());
		SmartDashboard.putBoolean("Succes", lidar.success);
		SmartDashboard.putBoolean("Success2", lidar.success2);
		//SmartDashboard.putNumber("Gyro", navx.getAngle());
	}
}

