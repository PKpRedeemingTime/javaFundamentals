package com.patrickKilpatrick.countries.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrickKilpatrick.countries.models.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>{
	@Query("SELECT c.name, l.language, l.percentage FROM Country c JOIN c.languages l WHERE language = ?1 ORDER BY percentage DESC")
	List<Object[]> findCountriesByLanguageDescending(String language);
	
	@Query("SELECT c.name, COUNT(c.id) AS numberOfCities FROM Country c JOIN c.cities ci GROUP BY c.id ORDER BY numberOfCities DESC")
	List<Object[]> findCountriesWithNumCities();
	
	@Query("SELECT c.name, l.language, l.percentage FROM Country c JOIN c.languages l WHERE l.percentage >= ?1 ORDER BY l.percentage DESC")
	List<Object[]> findLanguagesWithPercentageGreaterThan(Double percentage);
	
	@Query("SELECT c FROM Country c WHERE c.surfaceArea < ?1 AND c.population > ?2")
	List<Country> findCountryWithSurfaceSAreaBelowThanAndPopulationGreaterThan(Double surfaceArea, Integer population);
	
	@Query("SELECT c FROM Country c WHERE c.governmentForm = ?1 AND c.capital > ?2 AND c.lifeExpectancy > ?3")
	List<Country> findCountriesWithGovernmentFormAndCapitalAboveThanAndLifeExpectancyAboveThan(String governmentForm, Integer capital, Double lifeExpectancy);
	
	@Query("SELECT c.name, ci.name, ci.district, ci.population FROM Country c JOIN c.cities ci WHERE c.name = 'Argentina' AND ci.district = 'Buenos Aires' AND ci.population > ?1")
	List<Object[]> findCitiesInArgentinaInBuenosAiresWithPopulationGreaterThan(Integer population);
	
	@Query("SELECT c.region, COUNT(c.id) AS numberOfCountries FROM Country c GROUP BY c.region ORDER BY numberOfCountries DESC")
	List<Object[]> findNumberOfCountriesInEachRegion();

}
