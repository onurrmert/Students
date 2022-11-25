package com.onurmert.studentsnotes.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.onurmert.studentsnotes.Model.StudentsModel;
import java.util.List;

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insert(StudentsModel studentsModel);

    //bring all students
    @Query("SELECT * FROM student_table ")
    List<StudentsModel> getAllStudent();

    //only bring students in certain classes
    @Query("SELECT * FROM student_table WHERE studentClass = :studentCls ")
    List<StudentsModel> getAllClassStudent(String studentCls);

    //brings only one student
    @Query("SELECT * FROM student_table WHERE id = :id ")
    StudentsModel getOneStudent(int id);

    @Query("DELETE FROM student_table WHERE id = :id ")
    void delete(int id);

    @Update(entity = StudentsModel.class)
    void update1(StudentsModel studentsModel);
}
