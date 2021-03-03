package hiutrun.example.kmaschedule.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import hiutrun.example.kmaschedule.model.Schedule;
import io.reactivex.Single;

@Dao
public interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Schedule> schedules);

    @Query("Select * from schedule where date =:date ")
    Schedule getAllEvent(String date);
}
