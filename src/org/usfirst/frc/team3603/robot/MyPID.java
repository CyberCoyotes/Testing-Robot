package org.usfirst.frc.team3603.robot;

public class MyPID {
	private int total;
	
	public MyPID() {
		total = 0;
	}
	
	public double getSpeed(int offness) {
		double P = 1/Math.pow(offness, 2) * offness;
		
		/*
		double P = Math.pow(offness, 3) * 0.1;
		*/
		
		/*
		total = offness + total;
		double I = total * 0.001;
		double D = 
		*/
		
		return P;
	}
}
