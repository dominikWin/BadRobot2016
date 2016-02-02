package org.usfirst.frc.team1014.robot.utilities;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Encoder;

public class RPMUtil extends Thread {
	private static final int MEASUREMEMTS_TO_KEEP = 100;
	private static final int DELAY = 100;
	private static RPMUtil instance = null;
	
	private class Measurement {
		public int rotations;
	}
	
	private List<Measurement> measurements;
	private Encoder encoder;
	
	/**
	 * 
	 * @param time the last amount of measurements to use, currently 1/10 second each.
	 * @return the avarage RPM.
	 */
	public static int getAvarageRPM(int time) {
		if (time > MEASUREMEMTS_TO_KEEP || time < 1)
			time = MEASUREMEMTS_TO_KEEP;
		
		float rotations = instance.measurements.get(0).rotations - instance.measurements.get(time).rotations;
		
		float seconds = ((float) time) / 10f;
		
		float minutes = seconds / 60f;
		
		return (int) (rotations / minutes);
	}
	
	public static void init() {
		if (instance != null)
			return;
		
		instance = new RPMUtil();
		instance.start();
	}
	
	private RPMUtil() {
		measurements = new ArrayList<>();
		
		encoder = new Encoder(0, 1);
	}
	
	@Override
	public void run() {
		while (true) {
			
			Measurement m = new Measurement();
			m.rotations = encoder.get();
			
			measurements.add(0, m);
			while (measurements.size() > MEASUREMEMTS_TO_KEEP)
				measurements.remove(MEASUREMEMTS_TO_KEEP);
			
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
