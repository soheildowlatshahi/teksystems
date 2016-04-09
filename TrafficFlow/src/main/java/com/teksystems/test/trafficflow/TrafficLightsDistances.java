package com.teksystems.test.trafficflow;

/**
 * 
 * @author Soheil Dowlatshahi
 *
 */
public interface TrafficLightsDistances {
	
	/**
	 * 
	 * @param lightIndex
	 * @return the distance between (lightIndex - 1)th traffic light and (lightIndex)th traffic light 
	 */
	double getNextLightDistance(final int lightIndex);
	
	/**
	 * 
	 * @return the distance between last traffic light and the final target
	 */
	double getTargetDistance();
	
		
	

}
