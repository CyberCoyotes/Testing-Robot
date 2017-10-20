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
	
	public boolean isWorking() {
		Set<String> string = table.getKeys();
		String s = string.toString();
		working = (boolean) s.equals("[]") ? false : true;
		return working;
	}
	
	public double getX() {
		try {
			double[] x = table.getNumberArray("centerX"); //get the x values from the Kangaroo
			if(x.length != 0) { //If there are more than one x value...
				int numObjects = x.length; //Get the number of x's
				double average = 0; //Create an integer
				for(int obj = 0; obj < numObjects; obj++) {
					average = average + x[obj]; //Add all of the x values
				}
				average = average/numObjects; //Find the average x value
				return average;
			} else { //Otherwise there are no contours
				SmartDashboard.putString("Vision Status", "No contours");
				return -5;
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
	
	public double getSpeed() {
		try {
			double[] x = table.getNumberArray("centerX");
			if(x.length != 0) {
				int numObjects = x.length;
				double average = 0;
				for(int obj = 0; obj < numObjects; obj++) {
					average = average + x[obj];
				}
				average = average/numObjects;
				double speed = average*0.003125-1; //Scale the average between -1 and 1
				return speed;
			} else {
				SmartDashboard.putString("Vision Status", "Too many contours");
				return -5;
			}
			
		} catch(TableKeyNotDefinedException ex) {
			ex.printStackTrace();
			SmartDashboard.putString("Vision Status", "Table key not defined");
			return 0;
		} catch(ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
			SmartDashboard.putString("Vision Status", "Array index out of bounds");
			return 0;
		}
	}
	
	public double[] get(String key) {
		//Gets all of the data for a specific key
		try {
			return table.getNumberArray(key);
		} catch(TableKeyNotDefinedException ex) {
			ex.printStackTrace();
			SmartDashboard.putString("Vision Status", "Table key not defined: " + key);
			return null;
		}
	}
	
	public String getKeys() { //Gives a list of keys
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
	
	public void retry() { //Restart vision
		table = NetworkTable.getTable("GRIP/cyberCoyotes");
		Set<String> string = table.getKeys();
		String s = string.toString();
		SmartDashboard.putString("Keys", s);
	}
}
