package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.TestUtils.LiveDataTestUtil;
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
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private ToDocDataBase database;

    private Project mProject = new Project(1L, "Projet Test", 0xFFEADAD1);
    private Task mTask = new Task (1L,"TASK_DEMO",new Date().getTime());
    private Task mTask2 = new Task (1L,"TASK_DEMO_2",new Date().getTime());

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

    @Test
    public void insertAndGetTask() throws InterruptedException{

        this.database.projectDao().createProject(mProject);
        this.database.taskDao().createTask(mTask);
        this.database.taskDao().createTask(mTask2);

        List<Task> taskList = LiveDataTestUtil.getValue(this.database.taskDao().getTask());
        assertEquals(2, taskList.size());
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException{

        this.database.projectDao().createProject(mProject);
        this.database.taskDao().createTask(mTask);
        this.database.taskDao().createTask(mTask2);
        Task taskToRemove = LiveDataTestUtil.getValue(this.database.taskDao().getTask()).get(0);
        this.database.taskDao().deleteTask(taskToRemove.getId());

        List<Task> taskList = LiveDataTestUtil.getValue(this.database.taskDao().getTask());
        assertEquals(1, taskList.size());
    }
}
