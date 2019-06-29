package com.techinstance.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techinstance.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	Task findByTitle(String title);
}
