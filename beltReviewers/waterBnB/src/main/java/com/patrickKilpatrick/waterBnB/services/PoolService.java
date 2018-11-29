package com.patrickKilpatrick.waterBnB.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.waterBnB.models.Pool;
import com.patrickKilpatrick.waterBnB.models.Review;
import com.patrickKilpatrick.waterBnB.models.User;
import com.patrickKilpatrick.waterBnB.repositories.PoolRepository;

@Service
public class PoolService {
	@Autowired
	private PoolRepository poolRepo;
	
	public void createPool(Pool pool, User user) {
		pool.setUser(user);
		poolRepo.save(pool);
	}
	
	public void updatePool(Pool pool, User user) {
		pool.setUser(user);
		poolRepo.save(pool);
	}
	
	public Optional<Pool> getPool(Long id) {
		return poolRepo.findById(id);
	}
	
	public List<Pool> getAllPools() {
		return poolRepo.findAll();
	}
	
	public List<Review> getReviews(Long id) {
		Pool pool = poolRepo.findById(id).orElse(null);
		return pool.getReviews();
	}
	
	public List<Pool> findByAddress(String query) {
		return poolRepo.findByAddressContaining(query);
	}
}
