package org.usfirst.frc.team3603.robot;

import java.util.TimerTask;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;

public class Lidar {
	private I2C i2c;
	private byte[] distance;
	private java.util.Timer updater;
	public boolean success;
	public boolean success2;
	
	private final int LIDAR_ADDR = 0x62;
	private final int LIDAR_CONFIG_REGISTER = 0x00;
	private final int LIDAR_DISTANCE_REGISTER = 0x8f;
	
	public Lidar(Port port) {
		i2c = new I2C(port, LIDAR_ADDR);
		
		distance = new byte[2];
		
		updater = new java.util.Timer();
	}
	
	// Distance in cm
	public int getDistance() {
		System.out.print(distance[0]);
		System.out.println(distance[1]);
		return (int)Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
	}
	
	// Start 10Hz polling
	public void start() {
		updater.scheduleAtFixedRate(new LIDARUpdater(), 0, 100);
	}
	
	// Start polling for period in milliseconds
	public void start(int period) {
		updater.scheduleAtFixedRate(new LIDARUpdater(), 0, period);
	}
	
	public void stop() {
		updater.cancel();
		updater = new java.util.Timer();
	}
	
	// Update distance variable
	public void update() {
		success = !i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
		//Timer.delay(0.04); // Delay for measurement to be taken
		success2 = !i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
		//Timer.delay(0.005); // Delay to prevent over polling
	}
	
	// Timer task to keep distance updated
	private class LIDARUpdater extends TimerTask {
		public void run() {
			update();
			while(true) {
				update();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}