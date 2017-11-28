package org.usfirst.frc.team3603.robot;

import com.kauailabs.navx.frc.AHRS;

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
	
	RobotDrive mainDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	
	AHRS navx = new AHRS(Port.kUSB);
	
	Vision vision = new Vision();
	Joystick joy1 = new Joystick(0);
	Timer timer = new Timer();
	MyPID pid = new MyPID(0.005, 0, 0);
	
	@Override
	public void robotInit() {
		timer.start();
		backRight.setInverted(true);
    	frontRight.setInverted(true);
    	
    	pid.start();
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
			double rotation = pid.getPID(vision.getSpeed());
			mainDrive.mecanumDrive_Cartesian(0, 0, rotation, 0);
		} else {
			mainDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
		}
		
		read();
	}
	
	@Override
	public void testPeriodic() {
	}
	
	public void read() {
		SmartDashboard.putNumber("Vision speed", vision.getSpeed());
		SmartDashboard.putNumber("PID", pid.getPID(vision.getSpeed()));
		SmartDashboard.putString("Keys", vision.getKeys());
		SmartDashboard.putNumber("Derivitave", pid.derivitave);
	}
}

