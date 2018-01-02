package org.usfirst.frc.team3603.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	Victor backLeft = new Victor(1);
	Victor backRight = new Victor(2);
	Victor frontLeft = new Victor(3);
	Victor frontRight = new Victor(4);
	
	RobotDrive mainDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	
	Joystick joy1 = new Joystick(0);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	Encoder enc = new Encoder(9, 8, false, EncodingType.k2X);
	PIDController gyroCont = new PIDController(0.03, 0, 0, (PIDSource) gyro, (PIDOutput) mainDrive);
	
	Servo servo = new Servo(1);
	
	
	Vision vision = new Vision();
	double rate;
	
	Lidar lidar;
	
	@Override
	public void robotInit() {
		backRight.setInverted(true);
    	frontRight.setInverted(true);
    	
    	mainDrive.setSafetyEnabled(false);
    	lidar = new Lidar(I2C.Port.kMXP);
    	lidar.start();
    	enc.setDistancePerPulse((5.0*Math.PI)/256.0);
    	
    	servo.setAngle(90);
    	
    	Thread rateFinder = new Thread(() -> {
    		while(true) {
    			double d1 = (double) enc.get()/256.0*5.0*Math.PI;
    			d1 = d1/5280.0;
    			try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
    			double d2 = enc.get()/256.0*5.0*Math.PI;
    			d2 = d2/5280.0;
    			double r = (d2-d1)/0.01;
    			r = r/60.0/60.0;
    			rate = r;
    		}
    	});
    	rateFinder.start();
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}
	
	@Override
	public void teleopPeriodic() {
		double x = Math.pow(joy1.getRawAxis(0), 3);
		double y = Math.pow(joy1.getRawAxis(1), 3);
		double rot = Math.pow(joy1.getRawAxis(4), 3);
		if(driveThreshold(x, y, rot)) {
			mainDrive.mecanumDrive_Cartesian(x, y, rot, 0);
		}
		if(joy1.getRawButton(1)) {
			gyroCont.setSetpoint(vision.getAngle() + gyro.getAngle());
		}
		if(joy1.getRawButton(2)) {
			mainDrive.mecanumDrive_Cartesian(0, 0, gyroCont.get(), 0);
		}
		if(joy1.getRawButton(3)) {
			gyroCont.enable();
		}
		servo.setAngle(vision.getAngle());
		read();
		if(!vision.isWorking()) {
			vision.retry();
		}
	}
	
	boolean driveThreshold(double x, double y, double rot) {
		if(Math.abs(x) > 0.05 || Math.abs(y) > 0.05 || Math.abs(rot) > 0.05) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void testPeriodic() {
	}
	
	public void read() {
		SmartDashboard.putNumber("PIDOutput1", gyroCont.get());
		SmartDashboard.putNumber("Lidar Distance", lidar.getDistance());
		SmartDashboard.putNumber("MPH", rate);
	}
}