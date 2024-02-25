
package com.infy.todo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.infy.todo.model.TodoItemDTO;
import com.infy.todo.service.TodoService;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner{


	
	private final Log LOGGER = LogFactory.getLog(getClass());
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private TodoService todoService; // need to ask the Framework for the object type of the class
	
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
	
	@Override 
	public void run(String... args) throws Exception {

		//LOGGER.info(todoService.getItems());

		
		addItem();
		getItem();
		updateItem();
		deleteItem();
		//getAllItems();	
		//itemSize();
	}
	void addItem() throws Exception {
		TodoItemDTO item = new TodoItemDTO();
		item.setId(4);
		item.setTodo("");
		item.setTodo("Sky Dive");
		
		try {
			Integer id=todoService.addItem(item);
			LOGGER.info("Item Added with id" + id);
		} catch (Exception e) {
			LOGGER.error(environment.getProperty(e.getMessage()));
		}
	}
	
	void getItem()  {
		//Return the number of elements in the itemList
		try {
			LOGGER.info(todoService.getItem(2));
		} catch (Exception e) {
			LOGGER.error(environment.getProperty(e.getMessage()));
		}
	}
	
	void updateItem() {
		try {
			todoService.updateItem(2, "Party");
			LOGGER.info("Updated Id 2 with todo of Party");
		} catch (Exception e) {
			LOGGER.error(environment.getProperty(e.getMessage()));
		}
	}
	
	void deleteItem() {
		try {
			todoService.deleteItem(3);
			LOGGER.info("Success in deleting id 3");
		} catch(Exception e) {
			LOGGER.error(environment.getProperty(e.getMessage()));
		}
	}
	
//	void itemSize() throws Exception{
//		LOGGER.info(todoService.getItem());
//	}

}
