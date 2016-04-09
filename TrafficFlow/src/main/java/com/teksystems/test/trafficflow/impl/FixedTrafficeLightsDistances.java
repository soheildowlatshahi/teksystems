package com.teksystems.test.trafficflow.impl;

import com.teksystems.test.trafficflow.TrafficLightsDistances;

/**
 * @see com.teksystems.test.trafficflow.TrafficLightsDistances interface
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public final class FixedTrafficeLightsDistances implements TrafficLightsDistances {
	
	/**
	 * 
	 */
	private static final double DEFAULT_LIGHTS_DISTANCE = 150.0D;
	
	/**
	 * 
	 */
	public static final FixedTrafficeLightsDistances DEFAULT = newInstance(DEFAULT_LIGHTS_DISTANCE);
	
	
	/**
	 * 
	 */
	private double fixedDistance;
	
	/**
	 * 
	 * @param fixedDistance
	 */
	private FixedTrafficeLightsDistances(final double fixedDistance) {
		super();
		this.fixedDistance = fixedDistance;
	}

	/**
	 * 
	 */
	@Override
	public double getNextLightDistance(int lightIndex) {
		return fixedDistance;
	}
	
	/**
	 * 
	 */
	@Override
	public double getTargetDistance() {
		return fixedDistance;
	}
	
	/**
	 * 
	 * @param fixedDistance
	 * @return
	 */
	public static FixedTrafficeLightsDistances newInstance(final double fixedDistance) {
		return new FixedTrafficeLightsDistances(fixedDistance);
	}
	
}
