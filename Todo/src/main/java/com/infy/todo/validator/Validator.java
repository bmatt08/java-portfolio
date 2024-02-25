package com.infy.todo.validator;

import com.infy.todo.model.TodoItemDTO;

public class Validator {
	
	public static void validate(TodoItemDTO item) throws Exception {
		if(!validateId(item.getId())) throw new Exception("Validator.BAD_ID");
		if(!validateTodo(item.getTodo())) throw new Exception("Validator.BAD_TODO");
	}
	
	static Boolean validateTodo(String todo) {

		return todo!=null && !todo.isEmpty();	
		
	}
	
	static Boolean validateId(Integer id) {
		return id!=null && id > 0;
	}

}
