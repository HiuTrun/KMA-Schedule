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
public abstract class ScheduleDatabase extends RoomDatabase {

    public abstract ScheduleDao getScheduleDao();
    private static ScheduleDatabase scheduleDatabase;

    public synchronized static ScheduleDatabase getInstance(Context context){
        if(scheduleDatabase==null){
            // Create database
            scheduleDatabase =  Room.databaseBuilder(context.getApplicationContext(),ScheduleDatabase.class,"scheduledb.db")
                    .build();
        }
        return scheduleDatabase;
    }
}
