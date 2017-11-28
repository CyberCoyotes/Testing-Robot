package org.usfirst.frc.team3603.robot;

public class MyPID extends Thread {
	double m_p;
	double m_i;
	double m_d;
	double total = 0;
	double derivitave;
	Vision vision = new Vision();
	
	public MyPID(double p, double i, double d) {
		m_p = p;
		m_i = i;
		m_d = d;
	}
	
	public double getPID(double error) {
		if(error != -5) {
			total += total;
			double PID = m_p*(1/error) + m_i*total + m_d*derivitave;
			return PID;
		} else {
			return 0;
		}
	}
	
	@Override
	public void run() {
		while(true) {
			double d1 = vision.getSpeed();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			double d2 = vision.getSpeed();
			derivitave = (d2-d1)/0.1;
		}
	}
}
