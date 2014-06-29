package com.teksystems.test.trafficflow.impl;

import java.util.Arrays;

import com.teksystems.test.trafficflow.TrafficFlow;
import com.teksystems.test.trafficflow.TrafficLightsDistances;

/**
 * 
 * @see com.teksystems.test.trafficflow.TrafficFlow interface.
 * @author Soheil Dowlatshahi(soheil.dowlatshahi@yahoo.com)
 * 
 *
 */
public class NonRecursiveTrafficFlow implements TrafficFlow {
	
	
	
	/**
	 * 
	 */
	private  TrafficLightsDistances lightsDistances;
	
	
	/**
	 * 
	 */
	private final int carSpeed;
	
	/**
	 * 
	 */
	private final int[] trafficLightsTimings;
	
	
	
	
	/**
	 * 
	 * @param builder
	 */
	private NonRecursiveTrafficFlow(final Builder builder) {
		super();
		this.carSpeed = builder.carSpeed;
		
		//to build I
		this.trafficLightsTimings = Arrays.copyOf(builder.trafficLightsTimings, builder.trafficLightsTimings.length);
		this.lightsDistances = builder.lightsDistances;
	}
	
	/**
	 * 
	 */
	public int calculateCarTravelTime() {
		double passedTime = 0;
		 
		for (int i = 0; i < trafficLightsTimings.length; i++) {
			final int lightTiming = trafficLightsTimings[i];
			passedTime += getInterLightElapsedTime(i);
			passedTime = calculateLightDelayTime(passedTime, lightTiming);
		}
		
		return (int) Math.floor(passedTime + getLastIntervalTime());
	};
	
	/**
	 * 
	 * @param elapsedTime
	 * @param lightTiming
	 * @return
	 */
	private double calculateLightDelayTime(final double elapsedTime,  final int lightTiming) {
		long numberOfStateChange = (long) Math.floor(elapsedTime / lightTiming);
		
		if (!isTrafficLightGreen(numberOfStateChange)) {
			return calculateRedLightDelayTime(numberOfStateChange, lightTiming);
		 }
		
		return elapsedTime;
	}
	
	/**
	 * it can be customized to read from another array if requried
	 * @param lightIndex
	 * @return
	 */
	private double getInterLightElapsedTime(final int lightIndex) {
		return lightsDistances.getNextLightDistance(lightIndex) / carSpeed;
	}
	
	
	private double getLastIntervalTime() {
		return (lightsDistances.getTargetDistance() / carSpeed);
	}
		
	
	private double calculateRedLightDelayTime(long numberOfStateChange, int lightTiming) {
		return (numberOfStateChange + 1) * lightTiming;
	}
	
	/**
	 * 
	 * @param numberOfStateChange
	 * @return
	 */
	private boolean isTrafficLightGreen(final long numberOfStateChange) {
		return numberOfStateChange % 2 == 0;
	}
	
	/**
	 * 
	 * The builder class
	 *
	 */
	public static final class Builder implements TrafficFlow.Builder {
		
		
		private int carSpeed;
		
		
		private int[] trafficLightsTimings;
		
		
		private TrafficLightsDistances lightsDistances;
		
		
		@Override
		public Builder trafficLightsTimings(final int[] trafficLightsTimings) {
			this.trafficLightsTimings = trafficLightsTimings;
			return this;
		}
		
		@Override
		public Builder carSpeed(final int carSpeed) {
			this.carSpeed = carSpeed;
			return this;
		}
		
		public Builder lightsDistances(final TrafficLightsDistances lightsDistances) {
			this.lightsDistances = lightsDistances;
			return this;
		}
		
		@Override
		public TrafficFlow build() {
			return new NonRecursiveTrafficFlow(this);
		}
	}

}
