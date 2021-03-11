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
    private static ScheduleDatabase instance;

    public synchronized static ScheduleDatabase getInstance(Context context){
        if(instance==null){
            // Create database
            instance =  Room.databaseBuilder(context.getApplicationContext(),ScheduleDatabase.class,"scheduledb.db")
                    //.allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
