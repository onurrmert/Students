package com.onurmert.studentsnotes.ViewModel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.Room.StudentDatabase;

public class StudentDetailViewModel  extends ViewModel {

    public MutableLiveData<StudentsModel> studentModel = new MutableLiveData<>();

    public void getOneStudent(Context context, int id){

        StudentDatabase studentDatabase = StudentDatabase.getDatabase(context);

        StudentDatabase.databaseWriteExecutor.execute(()->{
            studentModel.postValue(studentDatabase.studentDao().getOneStudent(id));
        });
    }
}
