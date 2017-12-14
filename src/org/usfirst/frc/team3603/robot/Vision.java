package org.usfirst.frc.team3603.robot;

import java.util.Set;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

@SuppressWarnings("deprecation")
public class Vision {
	NetworkTable table; //Create a table
	boolean working;
	
	public Vision() {
		table = NetworkTable.getTable("GRIP/cyberVision"); //Begin the table with a key
		
		//See if there are keys in the table
		Set<String> string = table.getKeys();
		String s = string.toString();
		working = (boolean) s.equals("[]") ? false : true;
		
		SmartDashboard.putString("Keys", s); //Publish the keys
	}
	
	public boolean isWorking() { //Checks if the table has keys. If there aren't any keys, it isn't working
		Set<String> string = table.getKeys();
		String s = string.toString();
		working = (boolean) s.equals("[]") ? false : true;
		return working;
	}
	
	/**
	 * Returns the average x-coordinate of all objects that meet the criteria, between pixels 1-400.
	 * 1 is the leftmost pixel and 400 is the rightmost pixel
	 */
	public double getX() {
		try {
			double[] x = table.getNumberArray("centerX"); //get the x values from the Kangaroo
			if(x.length != 0) { //If there is one or more objects...
				int numObjects = x.length; //Get the number of x's
				double average = 0; //Create an integer to store the average x-coordinate
				for(int obj = 0; obj < numObjects; obj++) {
					average = average + x[obj]; //Add all of the x values
				}
				average = average/numObjects; //Find the average x value
				return average;//Return the average x-coordinate
			} else { //Otherwise there are no contours
				return -5; //If there are no contours, return -5
			}
		} catch(TableKeyNotDefinedException ex) { //If the key doesn't exist...
			ex.printStackTrace();
			SmartDashboard.putString("Vision Status", "Table key not defined");
			return -3;
		} catch(ArrayIndexOutOfBoundsException ex) { //If there aren't any values
			ex.printStackTrace();
			SmartDashboard.putString("Vision Status", "Array index out of bounds");
			return -4;
		}
	}
	
	
	/**
	 * This is the same as getX(), but it scales the x-coordinate from 1-400 to -1-1
	 * @return
	 */
	public double getSpeed() {
		return getX()*0.003125-1;
	}
	
	public double getAngle() {
		return getSpeed()*30;
	}
	
	public String getKeys() { //Gives a list of keys (for error proofing)
		Set<String> string = table.getKeys();
		String s = string.toString();
		return s;
	}
	
	public double getNumContours() { //Gives how many contours are found
		try {
			double[] x = table.getNumberArray("centerX");
			return x.length;
		} catch (TableKeyNotDefinedException ex){
			ex.printStackTrace();
			SmartDashboard.putString("Vision Status", "Table key not defined");
			return 0;
		}
	}
}
