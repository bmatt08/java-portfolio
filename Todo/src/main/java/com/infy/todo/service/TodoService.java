package com.infy.todo.service;

import java.util.List;

import com.infy.todo.model.TodoItemDTO;

public interface TodoService {
	
	Integer addItem(TodoItemDTO todoItemDTO) throws Exception;
	TodoItemDTO getItem(Integer id) throws Exception;
	void updateItem(Integer id, String newTodo) throws Exception;
	void deleteItem(Integer id) throws Exception;
	
	//List<TodoItemDTO> getItems() throws Exception;
	

}
