package hiutrun.example.kmaschedule.repository;

import android.content.Context;
import android.content.Intent;
import android.database.Observable;
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

    public void getTimetable(Context context, String username, String password, String hashpassword){
        RetrofitInstance.api.getTimetable(username,password,hashpassword).enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.isSuccessful()){
                    if(response.body().getError() == null ){
                        List<Schedule> list = response.body().getSchedule();
                        db.getScheduleDao().insert(list);
                        Intent intent = new Intent(context,MainActivity.class);;
                        String s = response.body().getName() + response.body().getStudentId();
                        intent.putExtra("model",s);
                        context.startActivity(intent);
                    }
                }
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
