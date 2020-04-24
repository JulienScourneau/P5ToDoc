package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getTask(long taskId);

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task_table")
    void deleteTask();


}
