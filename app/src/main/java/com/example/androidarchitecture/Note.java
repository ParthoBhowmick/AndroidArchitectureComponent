package com.example.androidarchitecture;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// @Entity annotation will create a table of the class automatically.. Name of the table will be
//same as name Class if tableName is not pass to the annotation
@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;

    private int priority;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    //@columnInfo(name = "") -> specifies user defined column name instead of class variable
    //@Ignore -> specifies the value of this column will not added to sqlLite db

}
