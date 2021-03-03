package hiutrun.example.kmaschedule.repository;

import android.database.Observable;
import android.util.Log;


import java.util.List;

import hiutrun.example.kmaschedule.api.RetrofitInstance;
import hiutrun.example.kmaschedule.db.ScheduleDatabase;
import hiutrun.example.kmaschedule.model.Lesson;
import hiutrun.example.kmaschedule.model.Model;
import hiutrun.example.kmaschedule.model.Schedule;
import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepository {

    private static final String TAG = ScheduleRepository.class.getSimpleName() ;
    private ScheduleDatabase db;

    public ScheduleRepository(ScheduleDatabase db) {
        this.db = db;
    }

    public void getTimetable(String username, String password, String hashpassword){
        RetrofitInstance.api.getTimetable(username,password,hashpassword).enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d(TAG, "onResponse: "+Thread.currentThread().getName());
                Log.d(TAG, "onResponse: "+ response.body());
                List<Schedule> list = response.body().getSchedule();
                db.getScheduleDao().insert(list);

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d(TAG, "onFailure: failed");
            }
        });
    }

    private List<Lesson> getAllEvent(String date){
        return db.getScheduleDao().getAllEvent(date).getLessons();
    }

//    private void insert(Schedule schedule){
//        Completable.fromCallable(()-> db.getScheduleDao().insert(schedule)).subscribe();
//    }
}
