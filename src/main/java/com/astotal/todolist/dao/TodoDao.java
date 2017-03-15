package com.astotal.todolist.dao;

import com.astotal.todolist.model.Todo;

import java.util.List;

public interface TodoDao {
    public void addTodo(Todo entry);

    public void updateTodo(Todo entry);

    public void removeTodo(Integer id);

    public Todo getTodoById(Integer id);

    public List<Todo> getTodoList(String filter);

    public List<Todo> getTodoList(String filter, int page, int size);

}
