/****************************************
 * 
 *	Encoder Code for ...
 *	@author Connor
 *
 ****************************************/

package org.usfirst.frc.team3603.robot;

import com.ctre.CANTalon;

public class MyEncoder {
	CANTalon talon;
	boolean inverted;
	
	public MyEncoder(int pin) {
		talon = new CANTalon(pin);
	}
	
	public MyEncoder(CANTalon inputTalon) {
		talon = inputTalon;
	}
	
	public void reset() {
		talon.setEncPosition(0);
	}
	public double getDistance() {
		double x = -(talon.getEncPosition()/4096.00000000000)*8.0*Math.PI;
		if(inverted) {
			x = -x;
		}
		return x;
	}
	public void invert(boolean in) {
		inverted = in;
	}
}
