package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mTask;

    public TaskRepository(TaskDao taskDao){
        this.mTaskDao = taskDao;
    }

    public LiveData<List<Task>> getTask(long taskId){
        return this.mTaskDao.getTask(taskId);
    }
}
