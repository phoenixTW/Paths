package com.paths;

public class Country {
	private String country;

	Country(String countryName) {
		this.country = countryName;
	}
	public boolean equals (Object o) {
		if(o==null) return false;
		if(getClass() != o.getClass()) return false;
		if(country == null) return false;
		return country.equals(((Country) o).country);
	}

	public int hashCode() {
		return country.hashCode();
	}

	public String getName () {
		return this.country;
	}
}