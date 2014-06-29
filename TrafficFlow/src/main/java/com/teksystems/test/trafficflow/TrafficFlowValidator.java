package com.teksystems.test.trafficflow;

/**
 * 
 * @author Soheil Dowlatshahi(soheil.dowlatshahi@yahoo.com)
 *
 */
public interface TrafficFlowValidator {
	
	/**
	 * 
	 * @param carSpeed
	 * @param trafficLightsTimings
	 */
	void validate(int carSpeed, int[] trafficLightsTimings);

}
