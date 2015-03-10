package com.paths;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaustavc on 3/10/2015.
 * CostManager keeps account of stations and its cost from a source station
 */
public class CostManager {
    private Map<City, Integer> costPerCity = new HashMap<City, Integer>();

    public void add(City city, int cost) {
        costPerCity.put(city, cost);
    }

    public int get(City to) {
        return costPerCity.get(to);
    }
}
