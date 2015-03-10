package com.paths;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by kaustavc on 3/10/2015.
 * Testing the costs of a route
 */
public class CostTest {
    @Test
    public void get_cost_should_give_300_for_Bangalore_to_Chennai() {
        RouteMap map = new RouteMap();
        map.insertPath("Bangalore", "Chennai", 300);

        assertEquals(300, map.getCost("Bangalore->Chennai"), 0.0);
    }

    @Test
    public void get_cost_should_give_5000_for_Bangalore_to_Iraq() {
        RouteMap map = new RouteMap();
        map.insertPath("Bangalore", "Iraq", 5000);

        assertEquals(5000, map.getCost("Bangalore->Iraq"), 0.0);
    }

    @Test
    public void get_cost_should_give_22000_for_Bangalore_to_USA() {
        RouteMap map = new RouteMap();
        map.insertPath("Bangalore", "Iraq", 5000);
        map.insertPath("Iraq", "England", 8000);
        map.insertPath("England", "USA", 9000);

        assertEquals(22000, map.getCost("Bangalore->Iraq->England->USA"), 0.0);
    }

    @Test
    public void sortByCosts_should_give_costs_for_Bangalore_to_hongkong() throws CityNotFoundException {
        RouteMap map = new RouteMap();
        map.insertPath("Bangalore", "Singapore", 300);
        map.insertPath("Chennai", "Delhi", 400);
        map.insertPath("Delhi", "Hongkong", 400);
        map.insertPath("Singapore", "Hongkong", 500);
        map.insertPath("Singapore", "Chennai", 200);

        String[] cityRespectiveCountry = {"Bangalore, India",
                "Chennai, India", "Delhi, India", "Singapore, Singapore", "Hongkong, Hongkong"};

        map.addCountry(cityRespectiveCountry);

        List<String> paths = new ArrayList<String>();
        paths.add("Bangalore[India]->Singapore[Singapore]->Hongkong[Hongkong]");
        paths.add("Bangalore[India]->Singapore[Singapore]->Chennai[India]->Delhi[India]->Hongkong[Hongkong]");

        List<String> derivedPaths = map.findAllPaths(new City("Bangalore"), new City("Hongkong"));
        TreeMap<String, Integer> sortedOrder = map.sortByCost(derivedPaths);
        String[] keySet = sortedOrder.keySet().toArray(new String[0]);

//        System.out.println(sortedOrder.values());
//        for (String path : sortedOrder.keySet()) {
//            System.out.println(sortedOrder.get(path));
//        }
//        assertEquals(800.0, (sortedOrder.get(sortedOrder.get(keySet[0]))), 0.0);
    }
}
