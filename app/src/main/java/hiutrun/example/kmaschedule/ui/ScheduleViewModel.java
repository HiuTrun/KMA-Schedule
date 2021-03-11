package hiutrun.example.kmaschedule.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.reactivestreams.Publisher;

import java.util.List;

import hiutrun.example.kmaschedule.adapter.EventAdapter;
import hiutrun.example.kmaschedule.model.Lesson;
import hiutrun.example.kmaschedule.model.Model;
import hiutrun.example.kmaschedule.model.Schedule;
import hiutrun.example.kmaschedule.model.Student;
import hiutrun.example.kmaschedule.repository.ScheduleRepository;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleViewModel extends ViewModel {
    public static final String TAG = ScheduleViewModel.class.getSimpleName();

    private ScheduleRepository repository;

    public ScheduleViewModel(ScheduleRepository repository) {
        this.repository = repository;
    }

    public void signIn(Context context, Student student){
        repository.signIn(context,student.getUsername(),student.getPassword(),"md5").enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.isSuccessful()){
                    if(response.body().getError() == null ){

                        List<Schedule> list = response.body().getSchedule();
                        Intent intent = new Intent(context,MainActivity.class);;
                        String s = response.body().getName();
                        intent.putExtra("model",s);
                        context.startActivity(intent);

                        // Insert all of items to db
                        repository.insert(list);
                        ;
                    }
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d(TAG, "onFailure: failed");
            }
        });
    }

    public void getTimetable(Context context, Long miliSeconds, EventAdapter adapter){
        repository.getTimetable(context,miliSeconds,adapter)
                .map(new Function<Schedule, List<Lesson>>() {
            @Override
            public List<Lesson> apply(@NonNull Schedule schedule) throws Exception {
                return schedule.getLessons();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Lesson>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Lesson> lessons) {
                        adapter.setLessons(lessons);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        adapter.setLessons(null);
                    }
                });

    }

    public void getAllEvents(CompactCalendarView calendar){
        repository.getAllEvents()
                .flatMap(schedules -> Observable.fromIterable(schedules))
                .map(new Function<Schedule, String>() {

                    @Override
                    public String apply(@io.reactivex.annotations.NonNull Schedule schedule) throws Exception {
                        return schedule.getDate();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        Event event = new Event(Color.WHITE, Long.parseLong(s), "event");
                        calendar.addEvent(event);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.e(TAG, "onError: ",e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
