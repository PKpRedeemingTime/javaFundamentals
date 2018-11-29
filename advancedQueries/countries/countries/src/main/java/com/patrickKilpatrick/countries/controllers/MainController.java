package com.patrickKilpatrick.countries.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.patrickKilpatrick.countries.models.City;
import com.patrickKilpatrick.countries.models.Country;
import com.patrickKilpatrick.countries.services.CountryService;

@Controller
public class MainController {
	@Autowired
	private CountryService countryService;
	
	@RequestMapping("/")
	public String index() {
		
//		List<Object[]> countries = countryService.findCountriesByLanguage("Slovene");		
//		for(Object[] row : countries) {
//			System.out.println(row[0]);
//			System.out.println(row[1]);
//			System.out.println(row[2]);
//		}
		
//		List<Object[]> numberOfCities = countryService.findCountriesWithNumCities();
//		for(Object[] row : numberOfCities) {
//			System.out.println(row[0]);
//			System.out.println(row[1]);
//		}
		
//		List<City> cities = countryService.findCitiesInCountryWithPopulationGreaterThan("Mexico", 500000);
//		for(City city : cities) {
//			System.out.println(city.getName());
//			System.out.println(city.getPopulation());
//		}
		
//		List<Object[]> languages = countryService.findLanguagesWithPercentageGreaterThan(89.);
//		for(Object[] row : languages) {
//			System.out.println(row[0]);
//			System.out.println(row[1]);
//			System.out.println(row[2]);
//		}
		
//		List<Country> countries = countryService.findCountriesWithSurfaceAreaBelowThanAndPopulationGreaterThan(501., 100000);
//		for(Country c : countries) {
//			System.out.println(c.getName());
//			System.out.println(c.getSurfaceArea());
//			System.out.println(c.getPopulation());
//		}
		
//		List<Country> countries = countryService.findCountriesWithGovermentFormAndCapitalGreaterThanAndLifeExpectancyGreaterThan("Constitutional Monarchy", 200, 75.);
//		for(Country c : countries) {
//			System.out.println(c.getName());
//			System.out.println(c.getGovernmentForm());
//			System.out.println(c.getCapital());
//			System.out.println(c.getLifeExpectancy());
//		}
		
//		List<Object[]> cities = countryService.findCitiesInArgentinaInBuenosAiresWithPopulationGreaterThan(500000);
//		for(Object[] row : cities) {
//			System.out.println(row[0]);
//			System.out.println(row[1]);
//			System.out.println(row[2]);
//			System.out.println(row[3]);
//		}
		
//		List<Object[]> countries = countryService.findNumberOfCountriesInEachRegion();
//		for(Object[] row : countries) {
//			System.out.println(row[0]);
//			System.out.println(row[1]);
//		}
		
		return "index.jsp";
	}

}
