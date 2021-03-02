package hiutrun.example.kmaschedule.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;


import hiutrun.example.kmaschedule.model.Schedule;

@Dao
public interface ScheduleDao {

    @Query("Select * from schedule where date =:date ")
    public LiveData<Schedule> getAllEvent(String date);
}
