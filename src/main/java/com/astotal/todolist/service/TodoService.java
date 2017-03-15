package com.astotal.todolist.service;

import com.astotal.todolist.model.Todo;

import java.util.List;

public interface TodoService {
    public void addOrUpdateTodo(Todo entry);

    public void removeTodo(Integer id);

    public Todo getTodoById(Integer id);

    public List<Todo> getTodoList(String filter);

    public List<Todo> getTodoList(String filter, int page, int size);

}
