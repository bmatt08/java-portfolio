package com.infy.todo.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TODOS")
public class TodoItem {
	@Id
	private Integer id;
	private String todo;
	@Override
	public int hashCode() {
		return Objects.hash(id, todo);
	}
	@Override
	public String toString() {
		return "TodoItem [id=" + id + ", todo=" + todo + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoItem other = (TodoItem) obj;
		return Objects.equals(id, other.id) && Objects.equals(todo, other.todo);
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
