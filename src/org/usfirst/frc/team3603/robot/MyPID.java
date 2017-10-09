package org.usfirst.frc.team3603.robot;

public class MyPID {
	private double total;
	private double m_time;
	private double m_change;
	private double m_p;
	private double m_i;
	private double m_d;
	
	public MyPID(int p, int i, int d) {
		total = 0;
		m_p = p;
		m_i = i;
		m_d = d;
	}
	
	public double getSpeed(double offness, double time, double change) {
		double P = m_p * 1/Math.pow(offness, 2) * offness;
		
		/*
		double P = p * Math.pow(offness, 3);
		*/
		
		total = offness + total;
		double I = total * m_i;
		
		time = Math.abs(time-m_time);
		change = Math.abs(change-m_change);
		double D = m_d * (time/change);
		
		double PID = P + I + D;
		
		if(PID > 1) {
			PID = 1;
		} else if(PID < -1) {
			PID = -1;
		}
		
		return PID;
	}
	
	public void preD(double time, double change) {
		m_time = time;
		m_change = change;
	}
}
