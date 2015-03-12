package com.paths;

class CityNotFoundException extends Exception {
	private String message;

	CityNotFoundException(String city) {
		this.message = "No City names \"" + city + "\" in Database";
	}

    @Override
    public String getMessage() {
        return message;
    }
}