package com.infy.todo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infy.todo.entity.TodoItem;
import com.infy.todo.model.TodoItemDTO;
import com.infy.todo.service.TodoService;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
	
	@PersistenceContext
	EntityManager entityManager;
		

	@Override
	public Integer addTodoItem(TodoItemDTO todoItemDTO) {
		
		TodoItem todoItem = new TodoItem();
		todoItem.setId(todoItemDTO.getId());
		todoItem.setTodo(todoItemDTO.getTodo());
		
		entityManager.persist(todoItem);
		
		return todoItem.getId();
	}
	
	@Override
	public TodoItemDTO getTodo(Integer id) {
		
		TodoItem todoItem = entityManager.find(TodoItem.class, id); //FETCHES FROM DB 
		if(todoItem!=null) {
			TodoItemDTO itemDTO = new TodoItemDTO();
			itemDTO.setId(todoItem.getId());
			itemDTO.setTodo(todoItem.getTodo());
			return itemDTO;
		}
		return null;
	}
	
	@Override
	public void updateTodo(Integer id, String newTodo) {
		TodoItem todoItem = entityManager.find(TodoItem.class, id);
		
		todoItem.setTodo(newTodo);
	}
	
	@Override
	public void deleteTodo(Integer id) {
		TodoItem todoItem = entityManager.find(TodoItem.class, id);
		
		entityManager.remove(todoItem);
	}
	
//	@Override
//	public List<TodoItemDTO> getItems(){
//		return null;
//	}

}
