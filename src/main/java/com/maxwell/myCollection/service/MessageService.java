package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.MessageEntity;

public interface MessageService {

	List<MessageEntity> findAll();

	Optional<MessageEntity> findById(Long id);

	MessageEntity addMessage(MessageEntity message);

	MessageEntity updateMessage(MessageEntity message);

	void removeMessage(Long id);

}