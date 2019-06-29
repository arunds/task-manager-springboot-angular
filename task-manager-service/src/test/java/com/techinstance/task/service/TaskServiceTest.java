package com.techinstance.task.service;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.techinstance.task.dao.TaskRepository;
import com.techinstance.task.model.Task;

//@RunWith(MockitoJUnitRunner.class)

@RunWith(SpringRunner.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRespoMock;
    
    @InjectMocks
    private TaskService taskService;
    
    @Test
    public void testGetAll() {
    	
    	given(taskRespoMock.findAll()).willReturn(Arrays.asList(createTask("Create UI")));
    	assertEquals(1, taskService.getAll().size());
    }
    
    @Test
    public void testGetOne() {
    	Task t = createTask("Create UI");
    	given(taskRespoMock.getOne(t.getId())).willReturn(t);
    	assertNotNull(taskService.get(t.getId()));
    }
    
    @Test
    public void testSave() {
    	Task t = createTask("Create UI");
    	given(taskRespoMock.save(t)).willReturn(t);
    	taskService.saveUpdate(t);
    }
    
    @Test
    public void testDelete() {
    	Task t = createTask("Create UI");
    	BDDMockito.doNothing().when(taskRespoMock).deleteById(t.getId());
    	taskService.delete(t.getId());
    }
    
    
	public static Task createTask(String title) {
		Task t = new Task();
		t.setTitle(title);
		return t;
	}
}
