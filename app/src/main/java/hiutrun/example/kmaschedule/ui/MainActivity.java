package hiutrun.example.kmaschedule.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.reactivestreams.Subscription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import hiutrun.example.kmaschedule.R;
import hiutrun.example.kmaschedule.adapter.EventAdapter;

import hiutrun.example.kmaschedule.db.Converters;
import hiutrun.example.kmaschedule.db.ScheduleDatabase;
import hiutrun.example.kmaschedule.model.Lesson;
import hiutrun.example.kmaschedule.model.Schedule;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private CompactCalendarView calendar;
    private RecyclerView rvEvent;
    private TextView tvName;
    private EventAdapter adapter;

    private final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        String s = getIntent().getStringExtra("model");
        Log.d(TAG, "onCreate: "+s);

        calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Schedule schedule = (Schedule) ScheduleDatabase.getInstance(getApplicationContext()).getScheduleDao().getAllEvent("1620691200000");
                List<Lesson> list = schedule.getLessons();
                Log.d(TAG, "onCreate: "+list.get(1).getAddress());
                Log.d(TAG, "onCreate: "+list.get(1).getSubjectName());
                adapter.setLessons(list);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });



    }

    private void init(){
        calendar = this.findViewById(R.id.calendarView);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setUseThreeLetterAbbreviation(true);
        Event ev1 = new Event(Color.BLACK, 1614770897000L, "Some extra data that I want to store.");
        List<Event> events = calendar.getEvents(1614770897000L);
        calendar.addEvent(ev1);

        Schedule schedule = (Schedule) ScheduleDatabase.getInstance(getApplicationContext()).getScheduleDao().getAllEvent("1620691200000");
        List<Lesson> list = schedule.getLessons();
        rvEvent = (RecyclerView)this.findViewById(R.id.rvEvent);
        adapter = new EventAdapter(this,list);
        rvEvent.setLayoutManager(new LinearLayoutManager(this));
        tvName = this.findViewById(R.id.tvName);
        rvEvent.setAdapter(adapter);
    }


}