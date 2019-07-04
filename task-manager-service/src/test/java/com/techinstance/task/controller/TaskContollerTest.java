package com.techinstance.task.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techinstance.task.model.Task;
import com.techinstance.task.service.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskContollerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	TaskService serviceMock;

	@Autowired
    private ObjectMapper mapper;
	
	@Test
	public void testGetTasks() throws Exception {

		given(serviceMock.getAll()).willReturn(Arrays.asList(createTask("Create UI")));
		
		Task t = createTask("Create UI");
		Arrays.asList(createTask("Create UI"));

		mockMvc.perform(get("/task-manager/tasks").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].title", is(t.getTitle())));
	}
	
	@Test
	public void testGetTask() throws Exception {

		given(serviceMock.get(Long.valueOf(1))).willReturn(createTask("Create UI"));
		
		Task t = createTask("Create UI");
		Arrays.asList(createTask("Create UI"));

		mockMvc.perform(get("/task-manager/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("title", is(t.getTitle())));
	}
	
	@Test
	public void testSaveTask() throws Exception {
		Task t = createTask("Create UI");
		BDDMockito.doNothing().when(serviceMock).saveUpdate(t);
		mockMvc.perform(post("/task-manager").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(t))).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteTask() throws Exception {
		given(serviceMock.get(Long.valueOf(1))).willReturn(createTask("Create UI"));
		mockMvc.perform(post("/task-manager/delete").contentType(MediaType.APPLICATION_JSON).content("1")).andExpect(status().isOk());
	}

	public static Task createTask(String title) {
		Task t = new Task();
		t.setId((long) 1);
		t.setTitle(title);
		return t;
	}

}
