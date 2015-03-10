package com.paths;

import org.junit.Test;
import static org.junit.Assert.*;

public class LibTest {

	@Test
	public void isCityPresent_should_return_true_for_an_invalid_city_in_routeMap (){
		RouteMap map = new RouteMap();
		map.insertPath("Bangalore", "Chennai");
		assertEquals(true, map.isCityPresent(new City("Bangalore")));
		assertEquals(true, map.isCityPresent(new City("Chennai")));
	}

	@Test
	public void isCityPresent_should_return_false_for_an_invalid_city_in_routeMap (){
		RouteMap map = new RouteMap();
		map.insertPath("Bangalore", "Chennai");
		assertEquals(false, map.isCityPresent(new City("Hyderabad")));
	}
}