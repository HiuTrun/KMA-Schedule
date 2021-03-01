package hiutrun.example.kmaschedule.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import hiutrun.example.kmaschedule.model.Lesson;
import io.reactivex.Flowable;

@Dao
interface ScheduleDao {

    @Query("SELECT * from schedule where date = :date")
    public LiveData<List<Lesson>> getAllEvent(String date);
}
