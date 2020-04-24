package com.cleanup.todoc.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TodocDataBase extends RoomDatabase {

    private static TodocDataBase instance;

    public abstract TaskDao taskDao();

    public static synchronized TodocDataBase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodocDataBase.class, "Todoc_database")
                    .addCallback(prepopulateDatabase())
                    .build();
        }
        return instance;
    }

    private static Callback prepopulateDatabase(){
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

            }
        };
    }
}
