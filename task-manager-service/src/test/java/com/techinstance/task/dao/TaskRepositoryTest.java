package com.techinstance.task.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.techinstance.task.model.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

	@Autowired
	TaskRepository taskRepository;

	@Before
	public void setup() {
		taskRepository.save(createTask("Create UI"));
		taskRepository.save(createTask("Create Service"));
	}

	@Test
	public void findAllTaskTest() {
		assertEquals(2, taskRepository.findAll().size());
	}

	@Test
	public void findByTitleTest() {
		assertNotNull(taskRepository.findByTitle("Create UI").getTitle());
	}

	@Test
	public void testSave() {
		Task t = createTask("Create CI-CD");
		assertNotNull(taskRepository.save(t));
	}

	@Test
	public void testDelete() {
		Task t = createTask("Create CI-CD");
		Task dbObj  = taskRepository.save(t);
		taskRepository.deleteById(dbObj.getId());
	}

	public static Task createTask(String title) {
		Task t = new Task();
		t.setTitle(title);
		return t;
	}
}
