package com.onurmert.studentsnotes.ViewModel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.Room.StudentDatabase;

public class InsertViewModel extends ViewModel {

    public MutableLiveData<StudentsModel> studentsModel = new MutableLiveData<StudentsModel>();

    public void insert(Context context, StudentsModel studentsModel){

        StudentDatabase studentDatabase = StudentDatabase.getDatabase(context);

        StudentDatabase.databaseWriteExecutor.execute(()->{
            studentDatabase.studentDao().insert(studentsModel);
        });
    }
}
