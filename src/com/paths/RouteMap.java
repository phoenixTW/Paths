package com.paths;

import javax.print.DocFlavor;
import java.util.*;

public class RouteMap {
	private Map<City, List<String>> routes = new HashMap<City, List<String>>();
	private List<String> possiblePath = new ArrayList<String>();
	private Map<City, Country> location = new HashMap<City, Country>();
    private Map<City, CostManager> costManager = new HashMap<City, CostManager>();

	public void insertPath (String source, String destination) {
		addSource(source, destination);
		routes.get(new City(source)).add(destination);
		routes.get(new City(destination)).add(source);
	}

	public boolean hasPath (City source, City destination) {
		return (routes.get(source) != null)
                && routes.get(source).indexOf(destination.getName()) >= 0;
	}

	private void addSource (String place1, String place2) {
		if(routes.get(new City(place1)) == null)
			routes.put(new City(place1), new ArrayList<String>());		

		if(routes.get(new City(place2)) == null)
			this.routes.put(new City(place2), new ArrayList<String>());
	}

	private void initStorage (String source) {		
		possiblePath = new ArrayList<String>();
		possiblePath.add(source);
	}

	public boolean hasPossiblePath (City source, City destination, List<String> visitedPaths) throws CityNotFoundException {
		if(!areCitiesPresent(source, destination)) return false;
	
		initStorage(source.getName());
		boolean hasAnyPath = trackPath(source, destination, visitedPaths);
		possiblePath = reversePath(source.getName());
		return hasAnyPath;
	}

	private boolean areCitiesPresent(City source, City destination) throws CityNotFoundException {
		if(!isCityPresent(source))
			throw new CityNotFoundException(source.getName());

		if(!isCityPresent(destination))
			throw new CityNotFoundException(destination.getName());

		return true;
	}

	public boolean trackPath(City source, City destination, List<String> visitedPaths) {
		visitedPaths.add(source.getName());

		if(hasPath(source, destination))
			return possiblePath.add(destination.getName());

		for (String city : routes.get(source))
			if((!(possiblePath.indexOf(city) >= 0))
				&& (!(visitedPaths.indexOf(city) >= 0)) 
				&& trackPath(new City(city),destination, visitedPaths))
				return possiblePath.add(city);

		return false;
	}

	private List<String> reversePath(String source) {
		List<String> path = new ArrayList<String>();
		path.add(source);
		for (int counter = (possiblePath.size() - 1); counter > 0; counter--) {
			path.add(possiblePath.get(counter));
		}

		return path;
	}

	public boolean isCityPresent (City city) {
		return routes.get(city) != null;
	}

	public String searchPath (City source, City destination) throws CityNotFoundException {
		List<String> visitedPaths = new ArrayList<String>();
		return manupulatePath(source, destination, visitedPaths);
	}

	private String manupulatePath(City source, City destination, List<String> visitedPaths) throws CityNotFoundException {
		hasPossiblePath(source, destination, visitedPaths);
		return stringifyPath();
	}

	private String stringifyPath () {
		String path = "";
		for (int counter = 0; counter < possiblePath.size(); counter++) {
			String cityName = possiblePath.get(counter);
			String cityLocation = cityName;
			
			if (location.get(new City(cityName)) != null) {
				String country = location.get(new City(cityName)).getName() ;
				cityLocation = cityName + "[" + country + "]";				
			}
			

			if(counter < possiblePath.size() - 1)
				path += cityLocation + "->";
			else
				path += cityLocation;
		}

		return path;
	}

	public void addCountry(String[] countryList) {
		for (String list : countryList) {
			String[] place = splitByComma(list);
			location.put(new City(place[0]), new Country(place[1]));
		}
	}

	private String[] splitByComma (String line) {
		String[] words = line.split(",");

		for (int count = 0; count < words.length; count++) {
			words[count] = words[count].trim();
		}

		return words;
	}

	public List<String> findAllPaths(City source, City destination) throws CityNotFoundException {
        List<String> collectionOfStations = new ArrayList<String>();
        List<List<String>> collectionOfPaths = new ArrayList<List<String>>();
        getPaths(collectionOfStations, collectionOfPaths, source, destination);
        List<String> derivedPaths = new ArrayList<String>();

        for (List<String> d_path : collectionOfPaths) {
            derivedPaths.add(join(d_path));
        }
        return derivedPaths;
	}

    private String join(List<String> derivedPaths) {
        String path = "";
        for (int counter = 0; counter < derivedPaths.size(); counter++) {
            String cityName = derivedPaths.get(counter);
            String cityLocation = cityName;

            if (location.get(new City(cityName)) != null) {
                String country = location.get(new City(cityName)).getName() ;
                cityLocation = cityName + "[" + country + "]";
            }


            if(counter < derivedPaths.size() - 1)
                path += cityLocation + "->";
            else
                path += cityLocation;
        }

        return path;
    }

    private void getPaths(List<String> collectionOfStations, List<List<String>> collectionOfPaths, City source, City destination) {
        collectionOfStations.add(source.getName());
        if (source.getName().equals(destination.getName())) {
            collectionOfPaths.add(new ArrayList<String>(collectionOfStations));
            collectionOfStations.remove(source.getName());
            return;
        }

        List<String> destinations = routes.get(source);
        for (int i = 0; i < destinations.size(); i++) {
            if (!collectionOfStations.contains(destinations.get(i))) {
                getPaths(collectionOfStations, collectionOfPaths, new City(destinations.get(i)), destination);
            }
        }
        collectionOfStations.remove(source.getName());
    }

    public void insertPath(String source, String destination, int cost) {
        insertPath(source, destination);
        addSourceCost(source, destination);
        costManager.get(new City(source)).add(new City(destination), cost);
        costManager.get(new City(destination)).add(new City(source), cost);
    }

    private void addSourceCost (String place1, String place2) {
        if(costManager.get(new City(place1)) == null)
            costManager.put(new City(place1), new CostManager());

        if(costManager.get(new City(place2)) == null)
            this.costManager.put(new City(place2), new CostManager());
    }

    public double getCost(String path) {
        String[] stations = path.split("->");
        int cost = 0;

        for (int count = 0; count < stations.length; count++) {
            City from = (count == 0) ? null : new City(stations[count - 1]);
            City to = new City(stations[count]);
            cost += getCostBetweenStations(from, to);
        }

        return cost;
    }

    private int getCostBetweenStations(City from, City to) {
        if (from == null) return 0;
        return costManager.get(from).get(to);
    }
    public double getCostHavingCountry(String path) {
        String[] stations = path.split("->");
        int cost = 0;

        for (int count = 0; count < stations.length; count++) {
            City from = (count == 0) ? null : new City(stations[count - 1].split("\\[")[0]);
            City to = new City(stations[count].split("\\[")[0]);
            cost += getCostBetweenStations(from, to);
        }

        return cost;
    }

    public TreeMap<String, Integer> sortByCost(List<String> derivedPaths) {
        Map<String, Integer> sortedOrder = new HashMap<String, Integer>();
        ValueComparator vc = new ValueComparator(sortedOrder);
        TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>();

        for (String path : derivedPaths) {
            int cost = (int) getCostHavingCountry(path);
            sortedOrder.put(path, cost);
        }

        sortedMap.putAll(sortedOrder);
        return sortedMap;
    }
}