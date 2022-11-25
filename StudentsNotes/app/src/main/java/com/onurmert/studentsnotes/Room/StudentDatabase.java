package com.onurmert.studentsnotes.Room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.onurmert.studentsnotes.Model.StudentsModel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {StudentsModel.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();

    private static volatile StudentDatabase INSTANCE;

    private static final int NUMBER_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_THREADS);

    public static StudentDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (StudentDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,
                                    StudentDatabase.class,
                                    "studentDatabse")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
