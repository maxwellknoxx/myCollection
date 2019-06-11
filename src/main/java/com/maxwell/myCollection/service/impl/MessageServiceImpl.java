package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.MessageEntity;
import com.maxwell.myCollection.repository.MessageRepository;
import com.maxwell.myCollection.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository repository;

	@Override
	public List<MessageEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<MessageEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public MessageEntity addMessage(MessageEntity message) {
		return repository.save(message);
	}

	@Override
	public MessageEntity updateMessage(MessageEntity message) {
		return repository.save(message);
	}

	@Override
	public void removeMessage(Long id) {
		repository.deleteById(id);
	}

}