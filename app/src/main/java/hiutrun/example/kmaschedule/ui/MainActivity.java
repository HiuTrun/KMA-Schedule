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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hiutrun.example.kmaschedule.R;
import hiutrun.example.kmaschedule.adapter.EventAdapter;

import hiutrun.example.kmaschedule.model.Lesson;


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
                List<Event> events = calendar.getEvents(dateClicked);
                List<Lesson> list = new ArrayList<>();

                list.add(new Lesson("Javascript","Javascript","Javascript"));
                list.add(new Lesson("Javascript","Javascript","Javascript"));
                list.add(new Lesson("Javascript","Javascript","Javascript"));
                adapter.setLessons(list);
                rvEvent.setAdapter(adapter);
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

        rvEvent = (RecyclerView)this.findViewById(R.id.rvEvent);
        adapter = new EventAdapter(this,null);
        rvEvent.setLayoutManager(new LinearLayoutManager(this));
        tvName = this.findViewById(R.id.tvName);
    }


}