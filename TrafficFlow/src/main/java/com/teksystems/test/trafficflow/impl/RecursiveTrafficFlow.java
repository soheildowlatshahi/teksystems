package com.teksystems.test.trafficflow.impl;



import java.util.Arrays;

import com.teksystems.test.trafficflow.TrafficFlow;
import com.teksystems.test.trafficflow.TrafficLightsDistances;


/**
 * 
 * @see com.teksystems.test.trafficflow.TrafficFlow interface
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public final class RecursiveTrafficFlow implements TrafficFlow {
	
	
	
	
	/**
	 * 
	 */
	private int carSpeed;
	
	/**
	 * 
	 */
	private int[] trafficLightsTimings;
	
	
	/**
	 * 
	 */
	private TrafficLightsDistances lightsDistances;

	
	/**
	 * 
	 */
	private RecursiveTrafficFlow() {
		throw new UnsupportedOperationException("Unsupported Constructor!");
	}

	
	private RecursiveTrafficFlow(final Builder builder) {
		super();
		this.carSpeed = builder.carSpeed;
		this.trafficLightsTimings = builder.trafficLightsTimings;
		this.lightsDistances = builder.lightsDistances;
	}

	/**
	 * 
	 * @param carSpeed
	 * @param traficLightsTimings
	 * @return the amount of time it takes the car to travel down the street
	 */
	public int calculateCarTravelTime() {
		return (int) Math.floor(calculateLightPassTime(trafficLightsTimings.length - 1) + getLastIntervalTime());
	}
	
	/**
	 * 
	 * @param carSpeed in meter per seconds
	 * @param trafficLightsTimings the timings of each traffic light in the path
	 * @param trafficeLightIndex 
	 * @return car travel time in seconds
	 */
	private double calculateLightPassTime(int trafficeLightIndex) {
		 double elpasedTime = getNextLightArriveTime(trafficeLightIndex);
		 		 
		 if (trafficeLightIndex > 0) {
			 elpasedTime += calculateLightPassTime(trafficeLightIndex - 1);
		 }	 
		 
		 long numberOfStateChange = (long) Math.floor(elpasedTime / trafficLightsTimings[trafficeLightIndex]);
						
		 if (isTrafficLightGreen(numberOfStateChange)) {
			return elpasedTime;
		 }
			
		return  (numberOfStateChange + 1) * trafficLightsTimings[trafficeLightIndex];
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
	 * @param lightIndex
	 * @return
	 */
	private double getNextLightArriveTime(int lightIndex) {
		return lightsDistances.getNextLightDistance(lightIndex) / carSpeed;
	}
	
	/**
	 * 
	 * @return
	 */
	private double getLastIntervalTime() {
		return (lightsDistances.getTargetDistance() / carSpeed);
	}
	
	
	/**
	 * 
	 * The builder class
	 *
	 */
	public static final class Builder implements TrafficFlow.Builder {
		
		/**
		 * 
		 */
		private int carSpeed;
		
		/**
		 * 
		 */
		private int[] trafficLightsTimings;
		
		
		/**
		 * 
		 */
		private TrafficLightsDistances lightsDistances;
		
		
		@Override
		public Builder carSpeed(final int carSpeed) {
			this.carSpeed = carSpeed;
			return this;
		}
		
		@Override
		public Builder trafficLightsTimings(final int[] trafficLightsTimings) {
			this.trafficLightsTimings = Arrays.copyOf(trafficLightsTimings, trafficLightsTimings.length);
			return this;
		}
		
		@Override
		public Builder lightsDistances(TrafficLightsDistances lightsDistances) {
			this.lightsDistances = lightsDistances;
			return this;
		}
		
		@Override
		public TrafficFlow build() {
			return new RecursiveTrafficFlow(this);
		}
	}
}

