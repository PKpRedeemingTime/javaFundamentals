package com.patrickKilpatrick.countries.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.countries.models.City;
import com.patrickKilpatrick.countries.models.Country;
import com.patrickKilpatrick.countries.repositories.CityRepository;
import com.patrickKilpatrick.countries.repositories.CountryRepository;
import com.patrickKilpatrick.countries.repositories.LanguageRepository;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private LanguageRepository languageRepo;
	@Autowired
	private CityRepository cityRepo;
	
	public List<Object[]> findCountriesByLanguage(String language){
		return countryRepo.findCountriesByLanguageDescending(language);
	}
	
	public List<Object[]> findCountriesWithNumCities(){
		return countryRepo.findCountriesWithNumCities();
	}
	
	public List<City> findCitiesInCountryWithPopulationGreaterThan(String country, Integer population){
		return cityRepo.findCitiesInCountryWithPopulationGreaterThan(country, population);
	}
	
	public List<Object[]> findLanguagesWithPercentageGreaterThan(Double percentage){
		return countryRepo.findLanguagesWithPercentageGreaterThan(percentage);
	}
	
	public List<Country> findCountriesWithSurfaceAreaBelowThanAndPopulationGreaterThan(Double surfaceArea, Integer population){
		return countryRepo.findCountryWithSurfaceSAreaBelowThanAndPopulationGreaterThan(surfaceArea, population);
	}
	
	public List<Country> findCountriesWithGovermentFormAndCapitalGreaterThanAndLifeExpectancyGreaterThan(String govermentForm, Integer capital, Double lifeExpectancy){
		return countryRepo.findCountriesWithGovernmentFormAndCapitalAboveThanAndLifeExpectancyAboveThan(govermentForm, capital, lifeExpectancy);
	}
	
	public List<Object[]> findCitiesInArgentinaInBuenosAiresWithPopulationGreaterThan(Integer population){
		return countryRepo.findCitiesInArgentinaInBuenosAiresWithPopulationGreaterThan(population);
	}
	
	public List<Object[]> findNumberOfCountriesInEachRegion(){
		return countryRepo.findNumberOfCountriesInEachRegion();
	}

}
