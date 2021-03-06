package hiutrun.example.kmaschedule.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import hiutrun.example.kmaschedule.R;
import hiutrun.example.kmaschedule.adapter.EventAdapter;

import hiutrun.example.kmaschedule.db.ScheduleDatabase;
import hiutrun.example.kmaschedule.model.Lesson;
import hiutrun.example.kmaschedule.model.Schedule;

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
                Long i = dateClicked.getTime() + 25200000;
                Log.d(TAG, "onDayClick: "+i);
                Log.d(TAG, "onDayClick: "+dateClicked.toString());
                Schedule schedule = (Schedule) ScheduleDatabase.getInstance(getApplicationContext()).getScheduleDao().getAllEvent(i.toString());
                List<Lesson> list;
                if(schedule!=null){
                    list = schedule.getLessons();
                }else
                {
                    list = new ArrayList<>();
                }
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

        List<Schedule> list = ScheduleDatabase.getInstance(this).getScheduleDao().getAllSchedule();
        for (Schedule item: list
             ) {
            Event event = new Event(Color.WHITE, Long.parseLong(item.getDate()), "Some extra data that I want to store.");
            calendar.addEvent(event);
        }
        rvEvent = (RecyclerView)this.findViewById(R.id.rvEvent);
        adapter = new EventAdapter(this,null);
        rvEvent.setLayoutManager(new LinearLayoutManager(this));
        tvName = this.findViewById(R.id.tvName);
        rvEvent.setAdapter(adapter);
    }


}