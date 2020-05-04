package com.cleanup.todoc.repository;

import android.arch.lifecycle.LiveData;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private final TaskDao mTaskDao;

    public TaskRepository(TaskDao taskDao){
        mTaskDao = taskDao;
    }

    public LiveData<List<Task>> getTask(){
        return mTaskDao.getTask();
    }

    public void createTask(Task task){
        mTaskDao.createTask(task);
    }

    public void deleteTask(long id){
        mTaskDao.deleteTask(id);
    }
}
