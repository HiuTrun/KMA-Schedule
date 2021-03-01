package hiutrun.example.kmaschedule.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import hiutrun.example.kmaschedule.model.Schedule;

@Database(
        entities = {Schedule.class},
        version = 1)
@TypeConverters(Converters.class)
abstract class ScheduleDatabase extends RoomDatabase {

    abstract ScheduleDao getScheduleDao();
    private static ScheduleDao scheduleDao;

    public synchronized static ScheduleDao  getInstance(Context context){
        if(scheduleDao==null){
            // Create database
            scheduleDao = (ScheduleDao) Room.databaseBuilder(context.getApplicationContext(),ScheduleDatabase.class,"scheduledb.db")
                    .build();
        }
        return scheduleDao;
    }
}
