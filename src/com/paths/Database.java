package com.paths;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Database{
	String fileLocation = null;
	String countryLocation = null;
	List<String> paths = new ArrayList<String>();

	Database (String pathName){
		this.fileLocation = pathName;
	}

	Database (String pathLocation, String countryFileLocation){
		this.fileLocation = pathLocation;
		this.countryLocation = countryFileLocation;
	}

	public String readFile (String filename) throws IOException {
		String text;
		File thisFile = new File("./resources/" + filename);
		FileReader fr = null;
		
		try {
			fr = new FileReader(thisFile);
		} catch(Exception e) {
			System.out.println("File Not Found");
		}

		BufferedReader br = new BufferedReader(fr);
		int length = (int)thisFile.length();
		char cbuf[] = new char[length];
		br.read(cbuf,0,length);
		text = new String (cbuf);
		
		return text;
	}

	public List<String> getPaths (String data) {
		String[] lines = data.split("\r\n");
//        paths = Arrays.asList(lines);

		for (String line : lines)
			paths.add(line);

		return paths;
	}

//	public List<String> getCountry (String data) {
//		String[] lines = data.split("\r\n");
//
//		for (String line : lines)
//			countries.add(line);
//
//		return countries;
//	}

	public RouteMap insertPath () throws IOException {
		String data = readFile(fileLocation);
		RouteMap map = new RouteMap();

        return (countryLocation != null) ? whenCountryPresent(map, data) : whenCountryNotPresent(map, data);
	}

	private RouteMap whenCountryPresent(RouteMap map, String cityData) throws IOException {
        map = whenCountryNotPresent(map, cityData);
		
		String data = readFile(countryLocation);
		String[] cityWithCountries = data.split("\r\n");

		map.addCountry(cityWithCountries);

		return map;
	}

	private RouteMap whenCountryNotPresent(RouteMap map, String data) throws IOException {
        List<String> collectionOfPaths = getPaths(data);

		for (String path : collectionOfPaths) {
 			String[] words = splitByComma(path);
            if (words.length == 3)
     			map.insertPath(words[0], words[1], Integer.parseInt(words[2]));
            else
                map.insertPath(words[0], words[1]);
		}

		return map;
	}

	private String[] splitByComma (String line) {
		String[] words = line.split(",");

		for (int count = 0; count < words.length; count++) {
			words[count] = words[count].trim();
		}

		return words;
	}
}