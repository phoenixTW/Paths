package com.paths;

public class City {
	private String city;

	City(String cityName) {
		this.city = cityName;
	}
	public boolean equals (Object o) {
		if(o==null) return false;
		if(getClass() != o.getClass()) return false;
		if(city == null) return false;
		return city.equals(((City) o).city);
	}

	public int hashCode() {
		return city.hashCode();
	}

	public String getName () {
		return this.city;
	}
}