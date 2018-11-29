package com.patrickKilpatrick.dojoOverflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.dojoOverflow.models.Answer;
import com.patrickKilpatrick.dojoOverflow.repositories.AnswerRepository;

@Service
public class AnswerService {
	@Autowired
	private AnswerRepository answerRepo;
	
	public Answer createAnswer(Answer answer) {
		return answerRepo.save(answer);
	}
}
