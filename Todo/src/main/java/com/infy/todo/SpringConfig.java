package com.infy.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.infy.todo.service.TodoService;
import com.infy.todo.service.TodoServiceImpl;

//HERE WE CAN USE THE "NEW" STATEMENTS; THIS IS THE SINGLE LOCATION WHERE THE IMPL CLASS NAMES ARE PRESENT
@Configuration
@ComponentScan(basePackages = "com.infy.todo")

public class SpringConfig {
	
	@Bean
	public TodoService todoService() {
		return new TodoServiceImpl();
	}

}
