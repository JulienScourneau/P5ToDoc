package com.cleanup.todoc.injections;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final Executor executor;

    public ViewModelFactory(TaskRepository taskRepository, ProjectRepository projectRepository, Executor executor){
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(taskRepository, projectRepository, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
