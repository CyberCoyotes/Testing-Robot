package org.usfirst.frc.team3603.robot;

public class MyPID extends Thread {
	double m_p;
	double m_i;
	double m_d;
	double total = 0;
	
	public MyPID(double p, double i, double d) {
		m_p = p;
		m_i = i;
		m_d = d;
	}
	
	public double getPID(double error) {
		if(error != -5) {
			total += total;
			double PID = m_p * (1/error) + m_i * total;
			return PID;
		} else {
			return 0;
		}
	}
	
	public void run() {
		
	}
}
