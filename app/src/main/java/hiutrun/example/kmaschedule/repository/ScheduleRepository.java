package hiutrun.example.kmaschedule.repository;

import hiutrun.example.kmaschedule.api.RetrofitInstance;
import hiutrun.example.kmaschedule.db.ScheduleDatabase;

public class ScheduleRepository {
    private ScheduleDatabase db;

    public void getTimetable(String username, String password, String hashpassword){
        RetrofitInstance.api.getTimetable(username,password,hashpassword);
    }

    public void getAllEvent(String date){
        db.getScheduleDao().getAllEvent(date);
    }
}
