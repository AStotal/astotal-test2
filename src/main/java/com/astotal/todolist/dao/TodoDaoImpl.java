package com.astotal.todolist.dao;

import com.astotal.todolist.model.Todo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDaoImpl implements TodoDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTodo(Todo entry) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(entry);
    }

    @Override
    public void updateTodo(Todo entry) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(entry);
    }

    @Override
    public void removeTodo(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Todo entry = (Todo) session.get(Todo.class, id);
        if (entry != null) {
            session.delete(entry);
        }
    }

    @Override
    public Todo getTodoById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Todo entry = (Todo) session.get(Todo.class, id);
        return entry;
    }

    private String getQueryByFilter(String filter){
        switch (filter){
            case "active" : return "FROM Todo WHERE isDone='0'";
            case "done" : return "FROM Todo WHERE isDone='1'";
            default : return "FROM Todo";
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Todo> getTodoList(String filter) {
        Session session = this.sessionFactory.getCurrentSession();
        Query q = session.createQuery(getQueryByFilter(filter));
        List<Todo> entries = q.list();

        return entries;
    }

    @Override
    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Todo> getTodoList(String filter, int page, int size) {
        Session session = this.sessionFactory.getCurrentSession();
        Query q = session.createQuery(getQueryByFilter(filter));

        q.setFirstResult((page - 1) * size);
        q.setMaxResults(size);
        List<Todo> entries = q.list();

        return entries;
    }
}
