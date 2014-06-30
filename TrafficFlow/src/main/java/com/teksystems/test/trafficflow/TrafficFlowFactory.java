package com.teksystems.test.trafficflow;



import com.teksystems.test.trafficflow.TrafficFlow.Builder;
import com.teksystems.test.trafficflow.impl.DefaultTrafficFlowValidator;
import com.teksystems.test.trafficflow.impl.FixedTrafficeLightsDistances;


/**
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public class TrafficFlowFactory {
	
	
			
	/**
	 * 
	 */
	private TrafficLightsDistances distances = FixedTrafficeLightsDistances.DEFAULT;
	
	/**
	 * 
	 */
	private final Class<? extends Builder> builderClass;
	
	
	/**
	 * 
	 */
	private TrafficFlowValidator validator = DefaultTrafficFlowValidator.newInstance();
	
	
	/**
	 * 
	 * @param builderClass
	 */
	public TrafficFlowFactory(final Class<? extends Builder> builderClass) {
		super();
		this.builderClass = builderClass;
	}
	
	
	public void setValidator(final TrafficFlowValidator validator) {
		this.validator = validator;
	}
	
	
	/**
	 * 
	 * @param lightsDistance
	 */
	public void setLightsDistances(TrafficLightsDistances distances) {
		this.distances = distances;
	}
	
	/**
	 * 
	 * @param carSpeed
	 * @param trafficLightsTiming
	 * @return
	 */
	public TrafficFlow createTrafficFlow(final int carSpeed, final int[] trafficLightsTimings) {
		
		if (validator != null) {
			validator.validate(carSpeed, trafficLightsTimings);
		}
				
		return buildTrafficFlow(carSpeed, trafficLightsTimings);
	}
	
	/**
	 * 
	 * @param carSpeed
	 * @param trafficLightsTimings
	 * @return
	 */
	private TrafficFlow buildTrafficFlow(final int carSpeed, final int[] trafficLightsTimings) {
		final Builder builder = createBuilder();
		
		builder.carSpeed(carSpeed);
		builder.trafficLightsTimings(trafficLightsTimings);
		builder.lightsDistances(distances);
		return builder.build();
	}
	
	/**
	 * 
	 * @return
	 */
	private Builder createBuilder() {
		try {
			return builderClass.newInstance();
		} catch (Exception ex) {
			throw new IllegalStateException("The buildClass" + builderClass + " cannot be instantiated!");
		} 
	}
	

}
