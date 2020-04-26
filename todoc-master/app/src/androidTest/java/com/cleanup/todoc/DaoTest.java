package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import androidx.room.Room;

import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    private TodocDataBase mDataBase;
    private static long TASK_ID = 1;
    private static Task TASK_DEMO = new Task (TASK_ID,1L,"Demo Task",new Date().getTime());

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.mDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception{
        this.mDataBase.close();
    }

    @Test
    public void insertTask(){
        this.mDataBase.taskDao().insertTask(TASK_DEMO);

    }
}
