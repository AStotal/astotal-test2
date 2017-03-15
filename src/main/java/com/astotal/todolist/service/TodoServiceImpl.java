package com.astotal.todolist.service;

import com.astotal.todolist.dao.TodoDao;
import com.astotal.todolist.model.Todo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by astotal on 14.03.17.
 */

@Service
public class TodoServiceImpl implements TodoService{
    private TodoDao todoDao;

    public void setTodoDao(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    @Transactional
    public void addOrUpdateTodo(Todo entry) {
        if (entry.getId() == null){
            this.todoDao.addTodo(entry);
        } else {
            this.todoDao.updateTodo(entry);
        }
    }

    @Override
    @Transactional
    public void removeTodo(Integer id) {
        this.todoDao.removeTodo(id);
    }

    @Override
    @Transactional
    public Todo getTodoById(Integer id) {
        return this.todoDao.getTodoById(id);
    }

    @Override
    @Transactional
    public List<Todo> getTodoList(String filter) {
        return this.todoDao.getTodoList(filter);
    }

    @Override
    @Transactional
    public List<Todo> getTodoList(String filter, int page, int size) {
        return this.todoDao.getTodoList(filter, page, size);
    }
}
