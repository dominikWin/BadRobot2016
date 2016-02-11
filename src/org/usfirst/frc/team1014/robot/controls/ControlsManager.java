package org.usfirst.frc.team1014.robot.controls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.usfirst.frc.team1014.robot.utilities.Logger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class ControlsManager {
//	// Button Mapping
//	public static final int BACK_LEFT_SPEED_CONTROLLER = 1;
//	public static final int FRONT_LEFT_SPEED_CONTROLLER = 5;
//	public static final int BACK_RIGHT_SPEED_CONTROLLER = 2;
//	public static final int FRONT_RIGHT_SPEED_CONTROLLER = 6;
//
//	public static final int SHOOTER_LEFT = 3;
//	public static final int SHOOTER_RIGHT = 4;
//	public static final int SHOOTER_ROTATE = 7;
//	public static final int RING_LIGHT = 9;
//
//	public static final int PUSHER = 8;
//
//	// DIO
//	public static final int ULTRA_PING = 1;
//	public static final int ULTRA_ECHO = 2;
//	public static final int RETRO_SENSOR = 0;
//
//	public static DriverStation driverStation;
//	public static XboxController primaryXboxController, secondaryXboxController;
//
//	public static void init() {
//		driverStation = DriverStation.getInstance();
//		primaryXboxController = new XboxController(0);
//		secondaryXboxController = new XboxController(1);
//	}
	
	private static Properties properties;
	
	private static final String MAP_FILE = "robot_map.ini";
	
	public static void init() {
		if (properties != null)
			return;
		
		properties = new Properties();
		File mapFile = new File(MAP_FILE);
		if (!mapFile.exists()) {
			Logger.log(Logger.Level.Error, ControlsManager.class.getName(), "Robot map file " + MAP_FILE + " does not exist");
			System.exit(-1);
			return;
		}
		
		if (!mapFile.canRead()) {
			Logger.log(Logger.Level.Error, ControlsManager.class.getName(), "Robot map file " + MAP_FILE + " can't be read");
			System.exit(-1);
			return;
		}
		
		FileInputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream(mapFile);
		} catch (FileNotFoundException e) {
			Logger.log(Logger.Level.Error, ControlsManager.class.getName(), "Robot map file " + MAP_FILE + " caused an IOError: " + e.toString());
			System.exit(-1);
			return;
		}
		
		try {
			properties.load(inputStream);
		} catch (IOException e1) {
			Logger.log(Logger.Level.Error, ControlsManager.class.getName(), "Robot map file " + MAP_FILE + " caused an IOError while being parsed: " + e1.toString());
			System.exit(-1);
			return;
		} catch (IllegalArgumentException e2) {
			Logger.log(Logger.Level.Error, ControlsManager.class.getName(), "Robot map file " + MAP_FILE + " contains an invalid unicode sequence: " + e2.toString());
			System.exit(-1);
			return;
		}
		
		verifyProperties();
		
		try {
			inputStream.close();
		} catch (IOException e1) {
			Logger.log(Logger.Level.Error, ControlsManager.class.getName(), "Robot map file " + MAP_FILE + " caused an IOError while closing: " + e1.toString());
			e1.printStackTrace();
		}
		
	}

	private static void verifyProperties() {
		//TODO VERIFY
		
		//TODO CREATE DEFAULT
	}
	
	public static int getValue(String key) {
		//TODO ADD FAILSAFES
		return Integer.parseInt(properties.getProperty(key));
	}
}
