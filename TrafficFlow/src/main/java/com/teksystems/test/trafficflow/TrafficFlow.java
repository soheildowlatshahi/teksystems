package com.teksystems.test.trafficflow;


/**
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public interface TrafficFlow {
	
	/**
	 * 
	 * @param carSpeed
	 * @param traficLightsTiming
	 * @return the amount of time it takes the car to travel down the street
	 */
	int calculateCarTravelTime();

	
	
	public interface Builder {
		
		Builder carSpeed(int carSpeed);
		
		Builder trafficLightsTimings(int[] trafficLightsTimings);
		
		Builder lightsDistances(TrafficLightsDistances distances);
		
		TrafficFlow build();		
	}

}
