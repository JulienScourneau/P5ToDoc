package com.cleanup.todoc.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.cleanup.todoc.model.Task;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);
}
