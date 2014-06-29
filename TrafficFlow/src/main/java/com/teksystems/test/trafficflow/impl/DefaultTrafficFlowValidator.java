package com.teksystems.test.trafficflow.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.teksystems.test.trafficflow.TrafficFlowValidator;

public class DefaultTrafficFlowValidator implements TrafficFlowValidator {
	/**
	 * 
	 */
	private static final Set<Integer> DEFAULT_VALID_CAR_SPEEDS = new HashSet<Integer>(Arrays.asList(new Integer[] {5, 10, 15, 20, 25, 30}));
	
	/**
	 * 
	 */
	private static final int DEFAULT_MAX_LIGHTS_COUNT = 50;
	
	
	/**
	 * 
	 */
	private static final int DEFAULT_MIN_LIGHTS_COUNT = 1;
	
	
	/**
	 * in seconds
	 */
	private static final int DEFAULT_MAX_LIGHT_TIMING = 60;
	
	/**
	 * in seconds
	 */
	private static final int DEFAULT_MIN_LIGHT_TIMING = 10;
		

	/**
	 * 
	 */
	private Set<Integer> validCarSpeeds = DEFAULT_VALID_CAR_SPEEDS; 
		
	
	/**
	 * 
	 */
	private int maxLightsCount = DEFAULT_MAX_LIGHTS_COUNT;
		
	
	/**
	 * 
	 */
	private int minLightsCount = DEFAULT_MIN_LIGHTS_COUNT;
	
	/**
	 * 
	 */
	private int maxLightTiming = DEFAULT_MAX_LIGHT_TIMING;
	
	/**
	 * 
	 */
	private int minLightTiming = DEFAULT_MIN_LIGHT_TIMING;
	
	
	/**
	 * 
	 */
	private static volatile TrafficFlowValidator instance = null;
	
	/**
	 * 
	 * @return
	 */
	public static TrafficFlowValidator newInstance() {
		if (instance == null) {
			instance = new DefaultTrafficFlowValidator();
		}
		
		return instance;
	}
	
	/**
	 * 
	 */
	private DefaultTrafficFlowValidator() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	@Override
	public void validate(int carSpeed, int[] trafficLightsTimings) {
		validateCarSpeed(carSpeed);
		validateTrafficLightsTimings(trafficLightsTimings);
	}

	
	/**
	 * 
	 * @param validCarSpeeds
	 */
	public void setValidCarSpeeds(final Set<Integer> validCarSpeeds) {
		this.validCarSpeeds = validCarSpeeds;
	}
	
	/**
	 * 
	 * @param minLightsCount
	 */
	public void setMinLightsCount(final int minLightsCount) {
		this.minLightsCount = minLightsCount;
	}
	
	/**
	 * 
	 * @param maxLightsCount
	 */
	public void setMaxLightsCount(final int maxLightsCount) {
		this.maxLightsCount = maxLightsCount;
	}
	
	/**
	 * 
	 * @param maxLightTiming
	 */
	public void setMaxLightTiming(final int maxLightTiming) {
		this.maxLightTiming = maxLightTiming;
	}
	
	/**
	 * 
	 * @param minLightTiming
	 */
	public void setMinLightTiming(final int minLightTiming) {
		this.minLightTiming = minLightTiming;
	}
	
	
	
	/**
	 * 
	 * @param trafficLightsTimings
	 */
	private void validateTrafficLightsTimings(final int[] trafficLightsTimings) {
		if (trafficLightsTimings == null) {
			throw new IllegalArgumentException("The trafficLightsTimings cannot be null!");
		}
		
		if(!isValidTrafficLightsCount(trafficLightsTimings.length)) {
			throw new IllegalArgumentException("The number of traffic lights is out of range!");
		}
		
		validateTrafficLightTimingElements(trafficLightsTimings);
	}
	
	/**
	 * 
	 * @param trafficLightsCount
	 * @return
	 */
	private boolean isValidTrafficLightsCount(final int trafficLightsCount) {
		if (trafficLightsCount == 0) {
			return false;
		}
		
		return (trafficLightsCount >= minLightsCount && trafficLightsCount <= maxLightsCount);
	}
	
	/**
	 * 
	 * @param trafficLightsTimings
	 */
	private void validateTrafficLightTimingElements(final int[] trafficLightsTimings) {
		for (int trafficLightTiming: trafficLightsTimings) {
			validateTrafficLightTimingElement(trafficLightTiming);
		}
	}
	
	/**
	 * 
	 * @param trafficLightTiming
	 */
	private void validateTrafficLightTimingElement(final int trafficLightTiming) {
		if (trafficLightTiming > this.maxLightTiming || trafficLightTiming < this.minLightTiming) {
			throw new IllegalArgumentException(
					String.format("The given trafficLightTiming (%d) is not between acceptable timing range %d to %d !", 
							trafficLightTiming, 
							minLightTiming, 
							maxLightTiming));
		}
	}
	
	
	
	/**
	 *  
	 * @param carSpeed
	 */
	private void validateCarSpeed(final int carSpeed) {
		if (carSpeed <= 0) {
			throw new IllegalArgumentException("The carSpeed should be greater than zero!");
		}
			
		if (validCarSpeeds != null  && !validCarSpeeds.contains(carSpeed)) {
			throw new IllegalArgumentException("The car speed is not in the defined valid car speed set!");
		}
	}

}
