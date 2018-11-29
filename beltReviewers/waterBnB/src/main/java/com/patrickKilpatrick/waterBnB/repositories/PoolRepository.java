package com.patrickKilpatrick.waterBnB.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrickKilpatrick.waterBnB.models.Pool;

@Repository
public interface PoolRepository extends CrudRepository<Pool, Long> {
	List<Pool> findAll();
	List<Pool> findByAddressContaining(String query);
}
