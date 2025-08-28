package com.xavier.todolist.service;

import com.xavier.todolist.model.Status;
import com.xavier.todolist.model.Task;
import com.xavier.todolist.repositoy.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //Busca todas as tarefas
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    //Busca tarefas por status
    public List<Task> findTasksByStatus(){
        return taskRepository.findAll();
    }

    //Salva uma nova tarefa
    public Task saveTask(Task task){
        task.setStatus(Status.PENDENTE);
        task.setDataCriacao(LocalDate.now());
        return taskRepository.save(task);
    }

    //Atualiza uma tarefa existente
    public Task updateTask(Long id, Task updatedTask){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()){
            throw new RuntimeException("Tarefa não encontrada com ID: " + id);
        }

        Task task = optionalTask.get();

        //Atualiza os campos da tarefa
        if (updatedTask.getTitulo() != null) task.setTitulo(updatedTask.getTitulo());
        if (updatedTask.getDescricao() != null) task.setDescricao(updatedTask.getDescricao());
        if (updatedTask.getPrioridade() != null) task.setPrioridade(updatedTask.getPrioridade());
        if(updatedTask.getStatus() != null){
            task.setStatus(updatedTask.getStatus());
            if(updatedTask.getStatus().equals(String.valueOf(Status.CONCLUIDA))){
                task.setDataConclusao(LocalDate.now());
            }
        }
        return taskRepository.save(task);
    }

    //Deleta uma tarefa
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada com ID: " + id);
        }
        taskRepository.deleteById(id);
    }
}
