package com.infy.todo.repository;


import com.infy.todo.model.TodoItemDTO;


public interface TodoRepository {
	Integer addTodoItem(TodoItemDTO todoItemDTO); //CREATE FROM CRUD 
	TodoItemDTO getTodo(Integer id); //READ FROM CRUD
	void updateTodo(Integer id, String newTodo); //UPDATE FROM CRUD
	void deleteTodo(Integer id); //DELETE FROM CRUD
	
	//List<TodoItemDTO> getItems();

}
