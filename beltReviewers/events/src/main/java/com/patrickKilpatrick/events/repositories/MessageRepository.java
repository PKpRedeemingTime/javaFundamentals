package com.patrickKilpatrick.events.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrickKilpatrick.events.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{

}
