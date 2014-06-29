package com.teksystems.test.trafficflow.impl;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.teksystems.test.trafficflow.TrafficFlow;
import com.teksystems.test.trafficflow.TrafficFlowFactory;
import com.teksystems.test.trafficflow.TrafficFlow.Builder;
import com.teksystems.test.trafficflow.impl.NonRecursiveTrafficFlow;
import com.teksystems.test.trafficflow.impl.RecursiveTrafficFlow;

import static org.junit.Assert.assertEquals;
/**
 * 
 * @see com.teksystems.test.trafficflow.TrafficFlow class.
 * @author Soheil Dowlatshahi(soheil.dowlatshahi@yahoo.com)
 * 
 */
@RunWith(Parameterized.class)
public class TraficFlowTest {
	
	
	/**
	 * 
	 * @return different Builders for implementing the Traffic Flow algorithm
	 */
	@Parameters
    public static Collection<?> data() {
		final Object[][] builderClasses = new Object[][] {{NonRecursiveTrafficFlow.Builder.class}, {RecursiveTrafficFlow.Builder.class}};
		return Arrays.asList(builderClasses);
    }
	
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
	private int actualCarTravelTime;
	
	/**
	 * 
	 */
	private TrafficFlowFactory factory;
	
	
	
	public TraficFlowTest(Class<Builder> builderClass) {
		super();
		this.factory = new TrafficFlowFactory(builderClass);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		carSpeed = 0;
		trafficLightsTimings = null;
		actualCarTravelTime = 0;
	}
	
	/**
	 * 
	 * speed will be either 5, 10, 15, 20, 25 or 30
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testZeroCarSpeed() {
		testCalculateCarTravelTime(0, new int[] {10, 10, 10}, 30);
	}
	
	/**
	 * lights will contain between 1 and 50 elements, inclusive
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testNoLights() {
		testCalculateCarTravelTime(30, new int[] {}, 30);
	}
	
	/**
	 * lights will contain between 1 and 50 elements, inclusive
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTooManyLights() {
		testCalculateCarTravelTime(30, new int[51] , 30);
	}
	
	/**
	 * 
	 * each element of lights will be an integer between 10 and 60, inclusive
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLowerLightsTiming() {
		testCalculateCarTravelTime(30, new int[] {9,10,10}, 30);
	}
	
	/**
	 * 
	 * each element of lights will be an integer between 10 and 60, inclusive
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testHigherLightsTiming() {
		testCalculateCarTravelTime(30, new int[] {61,10,10}, 30);
	}
	
	/**
	 * 
	 * @see com.teksystems.test.trafficeflow.TrafficeFlow.calculateCarTravelTime(int, int[])
	 * 
	 */
	@Test
	public void testCalculateCarTravelTime1() {
		testCalculateCarTravelTime(30, new int[] {10, 10, 10}, 30);
	}
	
	
	
	/**
	 * 
	 * @see com.teksystems.test.trafficeflow.TrafficeFlow.calculateCarTravelTime(int, int[])
	 * 
	 */
	@Test
	public void testCalculateCarTravelTime2() {
		testCalculateCarTravelTime(20, new int[] {10, 10, 10}, 35);
	}
	
	
	/**
	 * 
	 * @see com.teksystems.test.trafficeflow.TrafficeFlow.calculateCarTravelTime(int, int[])
	 * 
	 */
	@Test
	public void testCalculateCarTravelTime3() {
		testCalculateCarTravelTime(20, new int[] {10, 20, 30}, 30);
	}

	
	/**
	 * 
	 * @see com.teksystems.test.trafficeflow.TrafficeFlow.calculateCarTravelTime(int, int[])
	 * 
	 */
	@Test
	public void testCalculateCarTravelTime4() {
		testCalculateCarTravelTime(5, new int[] {10, 11, 12, 13, 14, 15}, 240);
	}
	

	/**
	 * 
	 * @see com.teksystems.test.trafficeflow.TrafficeFlow.calculateCarTravelTime(int, int[])
	 * 
	 */
	@Test
	public void testCalculateCarTravelTime5() {
		testCalculateCarTravelTime(5, new int[] {60, 60, 60, 60, 60, 60, 60, 60, 60, 60}, 630);
	}
	
	@Test
	public void testCalculateCarTravelTime6() {
		testCalculateCarTravelTime(25, new int[] {55,29,26,12,19,39,18,20,23,28,56,20,59,48,33,40,30,60,19}, 252);
	}
	
	/**
	 * 
	 * @param givencarSpeed  
	 * @param givenLightsTimings
	 * @param expectedCarTravelTime
	 */
	private void testCalculateCarTravelTime(final int givenCarSpeed, final int[] givenLightsTimings, final int expectedCarTravelTime) {
		givenParameters(givenCarSpeed, givenLightsTimings);
		calculateCarTravelTime();
		assertCarTravelTimeEquals(expectedCarTravelTime);
	}

	/**
	 * 
	 * @param carSpeed
	 * @param lightsTimings
	 */
	private void givenParameters(final int carSpeed, final int[] lightsTimings) {
		this.carSpeed = carSpeed;
		this.trafficLightsTimings = lightsTimings;
	}
	
	/**
	 * 
	 */
	private void calculateCarTravelTime() {
		this.actualCarTravelTime = newTrafficFlow().calculateCarTravelTime();
	}
	
	
	/**
	 * 
	 * @return
	 */
	private TrafficFlow newTrafficFlow() {
		return factory.createTrafficFlow(this.carSpeed, this.trafficLightsTimings);
	}
	
	/**
	 * 
	 * @param expectedTravelTime
	 */
	private void assertCarTravelTimeEquals(final int expectedTravelTime) {
		assertEquals(expectedTravelTime, actualCarTravelTime);
	}
	
	
	/**
	 * removing the used objects references
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		factory = null;
		actualCarTravelTime = 0;
		carSpeed = 0;
	}

}

