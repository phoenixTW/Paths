package com.paths;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class DatabaseTest {
	
	@Test
	public void readFile_should_read_contents_from_the_file () throws IOException{
		Database database = new Database("one.txt");
		String data = database.readFile("one.txt");
		assertEquals(data, "Bangalore, Mumbai,300\r\nBangalore, Chennai,300");
	}

	@Test
	public void getPaths_should_give_source_and_destination_path_list () throws IOException {
		Database database = new Database("one.txt");
		String data = database.readFile("one.txt");
		List<String> paths = database.getPaths(data);

		assertEquals(paths.get(0), "Bangalore, Mumbai,300");
		assertEquals(paths.get(1), "Bangalore, Chennai,300");
	}

	@Test
	public void insertPath_should_insert_datas_as_paths_in_routeMap () throws IOException {
		Database database = new Database("one.txt");
		RouteMap map = database.insertPath();
		assertEquals(map.hasPath(new City("Bangalore"), new City("Chennai")), true);
		assertEquals(map.hasPath(new City("Bangalore"), new City("Mumbai")), true);
	}

	@Test
	public void insertPath_should_insert_datas_as_paths_in_routeMap_when_country_txt_is_available () throws IOException {
		Database database = new Database("one.txt", "country.txt");
		RouteMap map = database.insertPath();
		assertEquals(true, map.hasPath(new City("Bangalore"), new City("Chennai")));
		assertEquals(true, map.hasPath(new City("Bangalore"), new City("Mumbai")));
	}
}