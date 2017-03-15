package com.astotal.todolist.controller;

import com.astotal.todolist.model.Todo;
import com.astotal.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Nullable;

@Controller
public class TodoController {
    private TodoService todoService;

    @Autowired(required = true)
    @Qualifier(value = "todoService")
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/todo")
    public String listTodo(Model model) {
        model.addAttribute("filter", "all");
        model.addAttribute("todoItem", new Todo());
        model.addAttribute("currentPage", 1);
        model.addAttribute("listTodo", this.todoService.getTodoList("all"));
        model.addAttribute("hasTodos", this.todoService.getTodoList("all", 2, 10).size() > 0);

        return "todo";
    }

    @RequestMapping(value = "/todo/{filter}/{page}")
    public String listTodo(@PathVariable("filter") String filter,
                           @PathVariable("page") int page, Model model) {
        model.addAttribute("todoItem", new Todo());
        model.addAttribute("filter", filter);
        model.addAttribute("currentPage", page);
        model.addAttribute("listTodo", this.todoService.getTodoList(filter, page, 10));
        model.addAttribute("hasTodos", this.todoService.getTodoList(filter, page + 1, 10).size() > 0);

        return "todo";
    }

    @RequestMapping(value = "/todo/add", method = RequestMethod.POST)
    public String addOrUpdateTodo(@ModelAttribute("todoItem") Todo todo, Model model) {
        this.todoService.addOrUpdateTodo(todo);
        model.addAttribute("listTodo", this.todoService.getTodoList("all"));

        return "redirect:/todo";
    }

    @RequestMapping(value = "/todo/remove/{id}", method = RequestMethod.GET)
    public String removeTodo(@PathVariable("id") int id) {
        this.todoService.removeTodo(id);

        return "redirect:/todo";
    }

    @RequestMapping(value = "/todo/edit/{id}", method = RequestMethod.GET)
    public String removeTodo(@PathVariable("id") int id, Model model) {
        model.addAttribute("filter", "all");
        model.addAttribute("todoItem", this.todoService.getTodoById(id));
        model.addAttribute("listTodo", this.todoService.getTodoList("all"));

        return "todo";
    }

    @RequestMapping(value = "/todo/finish/{id}", method = RequestMethod.GET)
    public String finishTodo(@PathVariable("id") int id, Model model) {
        model.addAttribute("filter", "all");
        model.addAttribute("todoItem", this.todoService.getTodoById(id));
        model.addAttribute("listTodo", this.todoService.getTodoList("all"));
        Todo todo = this.todoService.getTodoById(id);
        todo.setDone(true);
        this.todoService.addOrUpdateTodo(todo);

        return "redirect:/todo";
    }

    @RequestMapping(value = "/todo/notfinish/{id}", method = RequestMethod.GET)
    public String notFinishTodo(@PathVariable("id") int id, Model model) {
        model.addAttribute("filter", "all");
        model.addAttribute("todoItem", this.todoService.getTodoById(id));
        model.addAttribute("listTodo", this.todoService.getTodoList("all"));
        Todo todo = this.todoService.getTodoById(id);
        todo.setDone(false);
        this.todoService.addOrUpdateTodo(todo);

        return "redirect:/todo";
    }

}
