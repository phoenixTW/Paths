package com.paths;

class CityNotFoundException extends Exception {
	String message;

	CityNotFoundException(String city) {
		this.message = "No City names \"" + city + "\" in Database";
	}
}