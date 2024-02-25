package com.infy.todo.model;

import java.util.Objects;

public class TodoItemDTO {
	private Integer id;
	private String todo;
	
{
		}

@Override
public int hashCode() {
	return Objects.hash(id);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	TodoItemDTO other = (TodoItemDTO) obj;
	return Objects.equals(id, other.id);
}

@Override
public String toString() {
	return "TodoItem [id=" + id + ", todo=" + todo + "]";
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getTodo() {
	return todo;
}

public void setTodo(String todo) {
	this.todo = todo;
}
}
