package com.patrickKilpatrick.waterBnB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.waterBnB.models.Pool;
import com.patrickKilpatrick.waterBnB.models.Review;
import com.patrickKilpatrick.waterBnB.models.User;
import com.patrickKilpatrick.waterBnB.repositories.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepo;
	
	public void createReview(Review review, Pool pool, User user, int rating) {
		review.setPool(pool);
		review.setUser(user);
		int count = pool.getRatingCount() + 1;
		pool.setRatingCount(count);
		int sum = pool.getRatingSum() + rating;
		pool.setRatingSum(sum);
		reviewRepo.save(review);
	}

}
