package com.infy.todo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.infy.todo.model.TodoItemDTO;
import com.infy.todo.repository.TodoRepository;
import com.infy.todo.validator.Validator;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	TodoRepository todoRepository;

	@Override
	public Integer addItem(TodoItemDTO todoItemDTO) throws Exception{
		Validator.validate(todoItemDTO);
		if(todoRepository.getTodo(todoItemDTO.getId())!=null) throw new Exception("Service.ID_EXISTS");
		return todoRepository.addTodoItem(todoItemDTO);
	}
	
	@Override
	public TodoItemDTO getItem(Integer id) throws Exception{
		TodoItemDTO itemDTO = todoRepository.getTodo(id);
		if(itemDTO==null) throw new Exception("Service.NO_TODO_FOUND");
		return itemDTO;
	}
	
	@Override
	public void updateItem(Integer id, String newTodo) throws Exception{
		if(todoRepository.getTodo(id)==null) throw new Exception("Service.NO_TODO_FOUND");
		todoRepository.updateTodo(id, newTodo);
	}
	
	@Override public void deleteItem(Integer id) throws Exception{
		if(todoRepository.getTodo(id)==null) throw new Exception("Service.NO_TODO_FOUND");
		todoRepository.deleteTodo(id);
	}
	
//	@Override
//	public List<TodoItemDTO> getItems() throws Exception{
//		List<TodoItemDTO> itemList = todoRepository.getItems();
//		if(itemList.isEmpty()) throw new Exception("Service.NO_ITEMS");
//		return itemList;
//	}
}


