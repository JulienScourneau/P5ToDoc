package com.cleanup.todoc;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;

import com.cleanup.todoc.database.ToDocDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.Date;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private ToDocDataBase database;

    private Project mProject = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
    private Task mTask = new Task (1,mProject.getId(),"TASK_DEMO",new Date().getTime());

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ToDocDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetProject() throws InterruptedException{

        this.database.projectDao().createProject(mProject);

        Project project = LiveDataTestUtil.getValue(this.database.projectDao().getProject(mProject.getId()));
        assertTrue(project.getName().equals(mProject.getName()) && project.getId() == mProject.getId());
    }
}
