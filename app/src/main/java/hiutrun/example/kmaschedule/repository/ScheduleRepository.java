package hiutrun.example.kmaschedule.repository;

import android.content.Context;


import java.util.List;

import hiutrun.example.kmaschedule.adapter.EventAdapter;
import hiutrun.example.kmaschedule.api.RetrofitInstance;
import hiutrun.example.kmaschedule.db.ScheduleDatabase;
import hiutrun.example.kmaschedule.model.Model;
import hiutrun.example.kmaschedule.model.Schedule;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class ScheduleRepository {

    private static final String TAG = ScheduleRepository.class.getSimpleName() ;
    private ScheduleDatabase db;

    public ScheduleRepository(ScheduleDatabase db) {
        this.db = db;
    }

    public Call<Model> signIn(Context context, String username, String password, String hashpassword){
        return RetrofitInstance.api.signIn(username,password,hashpassword);
    }


//    private List<Lesson> getAllEvent(String date){
//        return db.getScheduleDao().getAllEvent(date).getLessons();
//    }

    public Single<Schedule> getTimetable(Context context, Long miliSeconds, EventAdapter adapter){
        return ScheduleDatabase.getInstance(context).getScheduleDao().getEvent(miliSeconds.toString());
    }
    public Disposable insert(List<Schedule> schedules){
        return db.getScheduleDao().insert(schedules).subscribeOn(Schedulers.io()).subscribe();
    }

    public Observable<List<Schedule>> getAllEvents(){
        return db.getScheduleDao().getAllEvents();
    }
}
