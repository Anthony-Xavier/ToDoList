package com.xavier.todolist.repositoy;

import com.xavier.todolist.model.Status;
import com.xavier.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(Status status);

}
