package com.onurmert.studentsnotes.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class StudentsModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "studentClass")
    private final String studentClass;

    @ColumnInfo(name = "notes")
    private final int notes;

    @ColumnInfo(name = "studentOpinion")
    private final String studentOpinion;

    public StudentsModel(int id, String name, String studentClass, int notes, String studentOpinion){
        this.id = id;
        this.name = name;
        this.studentClass = studentClass;
        this.notes = notes;
        this.studentOpinion = studentOpinion;
    }
    //constructer overloding
    @Ignore
    public StudentsModel(String name, String studentClass, int notes, String studentOpinion){
        this.name = name;
        this.studentClass = studentClass;
        this.notes = notes;
        this.studentOpinion = studentOpinion;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public int getNotes() {
        return notes;
    }

    public String getStudentOpinion() {
        return studentOpinion;
    }
}
