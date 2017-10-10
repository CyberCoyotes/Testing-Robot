package org.usfirst.frc.team3603.robot;

public class MyPID {
	private double total;
	private double m_time;
	private double m_change;
	private double m_p;
	private double m_i;
	private double m_d;
	
	public MyPID(double p, double i, double d) {
		total = 0;
		m_p = p;
		m_i = i;
		m_d = d;
	}
	
	public double getSpeed(double offness) {
		//double P = m_p * 1/Math.pow(offness, 2) * offness;
		
		
		double P = m_p * Math.pow(offness, 3);
		
		
		total = offness + total;
		double I = total * m_i;
		
		/*
		time = Math.abs(time-m_time);
		offness = Math.abs(offness-m_change);
		double D = m_d * (time/offness);
		
		/*
		double PID = P + I + D;
		*/
		
		double yes = P+I;
		
		if(yes > 1) {
			yes = 1;
		} else if(yes < -1) {
			yes = -1;
		}
		
		
		return -yes;
	}
}
