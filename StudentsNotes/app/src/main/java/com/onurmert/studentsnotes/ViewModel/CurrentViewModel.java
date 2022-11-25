package com.onurmert.studentsnotes.ViewModel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.Room.StudentDatabase;
import java.util.List;

public class CurrentViewModel extends ViewModel {

    public MutableLiveData<List<StudentsModel>> studentList = new MutableLiveData<>();

    public void getAllStudent(Context context){

        StudentDatabase studentDatabase = StudentDatabase.getDatabase(context);

        StudentDatabase.databaseWriteExecutor.execute( ()->{
            studentList.postValue(studentDatabase.studentDao().getAllStudent());
        });
    }
}
