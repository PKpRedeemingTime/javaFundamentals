package com.patrickKilpatrick.waterBnB.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrickKilpatrick.waterBnB.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>{

}
