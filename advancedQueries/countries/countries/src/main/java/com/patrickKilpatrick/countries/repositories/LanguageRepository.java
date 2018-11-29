package com.patrickKilpatrick.countries.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrickKilpatrick.countries.models.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long>{

}
