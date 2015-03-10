package com.paths;

import com.paths.City;

import java.util.List;
import java.io.*;

class Paths {
	public static void main(String[] args) throws IOException {
		City destination = new City(new String(args[args.length - 1]));
		City source = new City(new String(args[args.length - 2]));

		if(args.length == 7)
			processWithAllPaths(args, source, destination);
		if(args.length == 6)
			processWithCity(args, source, destination);
		if(args.length == 4)
			processWithoutCity(args, source, destination);
	}

	private static void processWithAllPaths(String[] args, City source, City destination) throws IOException {
		Database database = new Database(args[1], args[3]);
		RouteMap map = database.insertPath();

		try{
			List<String> paths = map.findAllPaths(source, destination);

			for (int counter = 0; counter < paths.size(); counter++) {
				System.out.println((counter + 1) + ". " + paths.get(counter));
			}
		}

		catch(CityNotFoundException e) {
			System.out.println(e.message);
		}				
	}

	private static void processWithoutCity(String[] args, City source, City destination) throws IOException  {
		Database database = new Database(args[1]);
		RouteMap map = database.insertPath();
		
		try{
			System.out.println(map.searchPath(source, destination));
		}

		catch(CityNotFoundException e) {
			System.out.println(e.message);
		}		
	}

	private static void processWithCity(String[] args, City source, City destination) throws IOException  {
		Database database = new Database(args[1], args[3]);
		RouteMap map = database.insertPath();

		try{
			System.out.println(map.searchPath(source, destination));
		}

		catch(CityNotFoundException e) {
			System.out.println(e.message);
		}		

	}
}