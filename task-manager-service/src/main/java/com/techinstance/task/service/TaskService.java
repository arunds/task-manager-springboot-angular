package com.techinstance.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techinstance.task.dao.TaskRepository;
import com.techinstance.task.model.Task;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepo;

	public List<Task> getAll() {
		return taskRepo.findAll();

	}

	public Task get(Long id) {
		return taskRepo.getOne(id);
	}

	@Transactional
	public void saveUpdate(Task task) {
		taskRepo.save(task);
	}

	@Transactional
	public void delete(Long id) {
		taskRepo.deleteById(id);

	}

}
