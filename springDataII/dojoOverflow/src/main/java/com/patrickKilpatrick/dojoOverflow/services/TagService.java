package com.patrickKilpatrick.dojoOverflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.dojoOverflow.models.Tag;
import com.patrickKilpatrick.dojoOverflow.repositories.TagRepository;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepo;
	
	public Tag findOrCreateTag(String subject) {
		Tag tagByName = tagRepo.findBySubject(subject);
		if(tagByName == null) {
			Tag tag = new Tag();
			tag.setSubject(subject);
			return tagRepo.save(tag);
		} else {
			return tagByName;
		}
	}
}
