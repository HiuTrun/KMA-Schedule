package hiutrun.example.kmaschedule.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import java.util.List;

import hiutrun.example.kmaschedule.api.RetrofitInstance;
import hiutrun.example.kmaschedule.db.ScheduleDatabase;
import hiutrun.example.kmaschedule.model.Lesson;
import hiutrun.example.kmaschedule.model.Model;
import hiutrun.example.kmaschedule.model.Schedule;
import hiutrun.example.kmaschedule.model.Student;
import hiutrun.example.kmaschedule.ui.MainActivity;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepository {

    private static final String TAG = ScheduleRepository.class.getSimpleName() ;
    private ScheduleDatabase db;

    public ScheduleRepository(ScheduleDatabase db) {
        this.db = db;
    }

    public void getTimetable(Context context, String username, String password, String hashpassword){
        RetrofitInstance.api.getTimetable(username,password,hashpassword).enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.isSuccessful()){
                    if(response.body().getError() == null ){
                        Log.d(TAG, "onResponse: hello");
                        List<Schedule> list = response.body().getSchedule();
                        Intent intent = new Intent(context,MainActivity.class);;
                        String s = response.body().getName();
                        intent.putExtra("model",s);
                        context.startActivity(intent);

                        // Insert all of items to db
                        db.getScheduleDao().insert(list).subscribeOn(Schedulers.io()).subscribe();
                    }
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d(TAG, "onFailure: failed");
            }
        });
    }

//    private List<Lesson> getAllEvent(String date){
//        return db.getScheduleDao().getAllEvent(date).getLessons();
//    }

//    private void insert(Schedule schedule){
//        Completable.fromCallable(()-> db.getScheduleDao().insert(schedule)).subscribe();
//    }
}
