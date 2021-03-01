package hiutrun.example.kmaschedule.model;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "lessons")
public class Lesson {
    @SerializedName("lesson")
    @Expose
    private String lesson;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("address")
    @Expose
    private String address;

    public String getLesson() {
        return lesson;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getAddress() {
        return address;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}