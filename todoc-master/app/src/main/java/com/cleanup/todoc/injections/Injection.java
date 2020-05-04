package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.ToDocDataBase;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskRepository provideTaskRepository(Context context){
        ToDocDataBase dataBase = ToDocDataBase.getInstance(context);
        return new TaskRepository(dataBase.taskDao());
    }

    public static ProjectRepository provideProjectRepository(Context context){
        ToDocDataBase dataBase = ToDocDataBase.getInstance(context);
        return new ProjectRepository(dataBase.projectDao());
    }

    public static Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context){
        TaskRepository dataSourceTask = provideTaskRepository(context);
        ProjectRepository dataSourceProject = provideProjectRepository(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceTask, dataSourceProject, executor);
    }
}
