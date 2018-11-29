package com.patrickKilpatrick.dojoOverflow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrickKilpatrick.dojoOverflow.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{
	Tag findBySubject(String subject);
}
