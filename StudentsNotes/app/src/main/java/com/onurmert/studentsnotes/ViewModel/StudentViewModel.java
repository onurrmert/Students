package com.onurmert.studentsnotes.ViewModel;

import android.content.Context;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.Room.StudentDatabase;

import java.util.List;

public class StudentViewModel extends ViewModel {

    public MutableLiveData<List<StudentsModel>> studentsList1 = new MediatorLiveData<>();

    public void getStudent1(Context context, String class1){

        StudentDatabase studentDatabase = StudentDatabase.getDatabase(context);

        StudentDatabase.databaseWriteExecutor.execute(()->{
            studentsList1.postValue(studentDatabase.studentDao().getAllClassStudent(class1));
        });
    }
}
