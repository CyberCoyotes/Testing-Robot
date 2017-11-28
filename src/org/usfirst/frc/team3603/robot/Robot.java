package org.usfirst.frc.team3603.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Robot extends IterativeRobot {
	Victor backLeft = new Victor(1);
	Victor backRight = new Victor(2);
	Victor frontLeft = new Victor(3);
	Victor frontRight = new Victor(4);
	
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
	}
}

