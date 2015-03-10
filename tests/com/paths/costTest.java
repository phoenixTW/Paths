package com.paths;

import org.junit.Test;

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
}
