package hiutrun.example.kmaschedule.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Entity(tableName = "schedule")
public class Schedule {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("lessons")
    @Expose
    private List<Lesson> lessons;

    private boolean isClicked;

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public String getDate() {
        return date;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}