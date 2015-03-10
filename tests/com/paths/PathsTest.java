package com.paths;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class PathsTest {
	@Test
	public void check_direct_path_between_two_cities () {
		RouteMap map = new RouteMap();
		map.insertPath("Bangalore", "Chennai");

		assertEquals(map.hasPath(new City("Bangalore"), new City("Chennai")), true);
		assertEquals(map.hasPath(new City("Bangalore"), new City("Delhi")), false);

		map.insertPath("Bangalore", "Delhi");
		assertEquals(map.hasPath(new City("Bangalore"), new City("Delhi")), true);
	}

	@Test
	public void check_any_possible_path_between_two_cities_01 () throws CityNotFoundException  {
		RouteMap map = new RouteMap();
		map.insertPath("Bangalore", "Chennai");
		map.insertPath("Bangalore", "Delhi");
		map.insertPath("Delhi", "Singapore");

		assertEquals(map.hasPossiblePath(new City("Bangalore"), new City("Chennai"), new ArrayList<String>()), true);
		assertEquals(map.hasPossiblePath(new City("Bangalore"), new City("Singapore"), new ArrayList<String>()), true);
		assertEquals(map.hasPossiblePath(new City("Singapore"), new City("Delhi"), new ArrayList<String>()), true);
	}

	@Test
	public void check_any_possible_path_between_two_cities_02 () throws CityNotFoundException  {
		RouteMap map = new RouteMap();
		map.insertPath("Bangalore", "Chennai");
		map.insertPath("Bangalore", "Delhi");
		map.insertPath("Delhi", "Singapore");

		assertEquals(true, map.hasPossiblePath(new City("Singapore"), new City("Bangalore"), new ArrayList<String>()));
	}

	@Test
	public void display_any_possible_path_between_two_cities () throws CityNotFoundException  {
		RouteMap map = new RouteMap();
		map.insertPath("Bangalore", "Chennai");
		map.insertPath("Bangalore", "Delhi");
		map.insertPath("Delhi", "Singapore");

		String[] cityRespectiveCountry = {"Bangalore, India", "Chennai, India", "Delhi, India", "Singapore, Singapore"};
		map.addCountry(cityRespectiveCountry);
		String path = "Singapore[Singapore]->Delhi[India]->Bangalore[India]";

		assertEquals(path, map.searchPath(new City("Singapore"), new City("Bangalore")));
	}

	@Test
	public void display_any_possible_path_between_Hongkong_and_Bangalore_and_Vice_versa () throws CityNotFoundException {
		RouteMap map = new RouteMap();
		map.insertPath("Bangalore", "Chennai");
		map.insertPath("Bangalore", "Delhi");
		map.insertPath("Delhi", "Singapore");
		map.insertPath("Singapore", "Hongkong");

		String[] cityRespectiveCountry = {"Bangalore, India", 
		"Chennai, India", "Delhi, India", "Singapore, Singapore", "Hongkong, Hongkong"};

		map.addCountry(cityRespectiveCountry);

		String path = "Hongkong[Hongkong]->Singapore[Singapore]->Delhi[India]->Bangalore[India]";
		assertEquals(path, map.searchPath(new City("Hongkong"), new City("Bangalore")));

		path = "Bangalore[India]->Delhi[India]->Singapore[Singapore]->Hongkong[Hongkong]";
		assertEquals(path, map.searchPath(new City("Bangalore"), new City("Hongkong")));		
	}

	// @Test
	// public void display_all_possible_path_between_Bangalore_and_Hongkong() throws CityNotFoundException {
	// 	RouteMap map = new RouteMap();
	// 	map.insertPath("Bangalore", "Chennai");
	// 	map.insertPath("Bangalore", "Singapore");
	// 	map.insertPath("Chennai", "Delhi");
	// 	map.insertPath("Delhi", "Singapore");
	// 	map.insertPath("Singapore", "Hongkong");
	// 	map.insertPath("Singapore", "Chennai");

	// 	String[] cityRespectiveCountry = {"Bangalore, India", 
	// 	"Chennai, India", "Delhi, India", "Singapore, Singapore", "Hongkong, Hongkong"};

	// 	map.addCountry(cityRespectiveCountry);

	// 	List<String> paths = new ArrayList<String>();
	// 	paths.add("Bangalore[India]->Chennai[India]->Delhi[India]->Singapore[Singapore]->Hongkong[Hongkong]");
	// 	paths.add("Bangalore[India]->Singapore[Singapore]->Hongkong[Hongkong]");

	// 	assertEquals(paths, map.findAllPaths(new City("Bangalore"), new City("Hongkong")));
	// }

	// @Test
	// public void display_all_possible_path_between_Hongkong_and_Bangalore() throws CityNotFoundException {
	// 	RouteMap map = new RouteMap();
	// 	map.insertPath("Bangalore", "Chennai");
	// 	map.insertPath("Bangalore", "Singapore");
	// 	map.insertPath("Chennai", "Delhi");
	// 	map.insertPath("Delhi", "Singapore");
	// 	map.insertPath("Singapore", "Hongkong");
	// 	map.insertPath("Singapore", "Chennai");

	// 	String[] cityRespectiveCountry = {"Bangalore, India", 
	// 	"Chennai, India", "Delhi, India", "Singapore, Singapore", "Hongkong, Hongkong"};

	// 	map.addCountry(cityRespectiveCountry);

	// 	List<String> paths = new ArrayList<String>();
	// 	paths.add("Bangalore[India]->Chennai[India]->Delhi[India]->Singapore[Singapore]->Hongkong[Hongkong]");
	// 	paths.add("Bangalore[India]->Singapore[Singapore]->Hongkong[Hongkong]");

	// 	assertEquals(paths, map.findAllPaths(new City("Bangalore"), new City("Hongkong")));
	// }
	@Test
	public void display_all_possible_path_between_Bangalore_and_Hongkong_with_different_route() throws CityNotFoundException {
		RouteMap map = new RouteMap();
		map.insertPath("Bangalore", "Singapore");
		map.insertPath("Chennai", "Delhi");
		map.insertPath("Delhi", "Hongkong");
		map.insertPath("Singapore", "Hongkong");
		map.insertPath("Singapore", "Chennai");

		String[] cityRespectiveCountry = {"Bangalore, India", 
		"Chennai, India", "Delhi, India", "Singapore, Singapore", "Hongkong, Hongkong"};

		map.addCountry(cityRespectiveCountry);

		List<String> paths = new ArrayList<String>();
		paths.add("Bangalore[India]->Singapore[Singapore]->Chennai[India]->Delhi[India]->Hongkong[Hongkong]");
		paths.add("Bangalore[India]->Singapore[Singapore]->Hongkong[Hongkong]");

		// assertEquals(map.hasPossiblePath(new City("Chennai"), new City("Hongkong"), new ArrayList<String>()), true);

		List<String> derivedPaths = map.findAllPaths(new City("Bangalore"), new City("Hongkong"));
		assertEquals(paths.get(1), derivedPaths.get(0));
		System.out.println(derivedPaths);
		// assertEquals(paths.get(0), derivedPaths.get(1));
	}
}