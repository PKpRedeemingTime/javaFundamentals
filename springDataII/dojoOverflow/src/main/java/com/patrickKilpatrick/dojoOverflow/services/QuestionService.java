package com.patrickKilpatrick.dojoOverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.dojoOverflow.models.Question;
import com.patrickKilpatrick.dojoOverflow.repositories.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepo;
	
	public Question createQuestion(Question question) {
		return questionRepo.save(question);
	}
	
	public List<Question> getAll() {
		return questionRepo.findAll();
	}
	
	public Optional<Question> getQuestion(Long id) {
		return questionRepo.findById(id);
	}
}
