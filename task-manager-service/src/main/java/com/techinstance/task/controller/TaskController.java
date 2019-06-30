package com.techinstance.task.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techinstance.task.model.Task;
import com.techinstance.task.service.TaskService;


@CrossOrigin
@RestController
@RequestMapping("/task-manager")
public class TaskController {

    @Autowired
    TaskService service;
    
    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
    	logger.info("Fetch all the tasks from  # ");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(value = "id") String id) {
    	logger.info("Fetch tasks   # "+id);
        return service.get(Long.valueOf(id));
    }


    @PostMapping
    public void saveTask(@RequestBody Task task) {
    	logger.info("Saving task   # "+task.getTitle());
        service.saveUpdate(task);
    }


    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody String id) {
    	logger.info("Deleting task   # "+id);
        if (id != null) {
            //service.delete(Long.valueOf(id));
            Task task = service.get(Long.valueOf(id));
            task.setStatus("ENDED");
            service.saveUpdate(task);
        }
        return ResponseEntity.ok().build();

    }



}
