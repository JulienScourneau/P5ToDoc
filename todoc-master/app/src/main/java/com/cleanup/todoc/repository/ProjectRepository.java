package com.cleanup.todoc.repository;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;


public class ProjectRepository {

   private ProjectDao mProjectDao;

   public ProjectRepository(ProjectDao projectDao){
       mProjectDao = projectDao;
   }

   public LiveData<Project> getProject(long projectId){
       return mProjectDao.getProject(projectId);
   }
}
