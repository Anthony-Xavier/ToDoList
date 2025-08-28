package com.xavier.todolist.controller;

import com.xavier.todolist.model.Status;
import com.xavier.todolist.model.Task;
import com.xavier.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //Post - Cria uma nova tarefa
    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    //Get - listar todas as tarefas ou por status
    @GetMapping
    public List<Task> getTasks(@RequestParam(required = false) Status status){
        if(status != null){
            return taskService.findTasksByStatus();
        }
        return taskService.findAllTasks();
    }

    //Put - Atualiza uma tarefa existente
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    //Delete - Deleta uma tarefa
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Tarefa com Id" + id + " deletada com sucesso!";
    }
}
