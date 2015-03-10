package com.paths;

import java.util.List;
import java.io.*;
import java.util.TreeMap;

class Paths {
	public static void main(String[] args) throws IOException {
		City destination = new City(args[args.length - 1]);
		City source = new City(args[args.length - 2]);

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
            TreeMap<String, Integer> sortedOrder = map.sortByCost(paths);
            int count = 0;
            String[] sortedArray = new String[0];
            sortedArray = sortedOrder.keySet().toArray(sortedArray);

            for (int i = sortedArray.length-1; i > -1; i--){
                System.out.println(++count + ". " + sortedArray[i]);
                System.out.println("Total Costs: " + sortedOrder.get(sortedArray[i]));

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
            String path = map.searchPath(source, destination);
            double cost = map.getCost(path);
			System.out.println(path + "\n" + "Total Costs: " + cost);
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