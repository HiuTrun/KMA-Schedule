package hiutrun.example.kmaschedule.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import hiutrun.example.kmaschedule.model.Schedule;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List<Schedule> schedules);

    @Query("Select * from schedule where date =:date ")
    Schedule getAllEvent(String date);

    @Query("Select * from schedule")
    List<Schedule> getAllSchedule();
}
