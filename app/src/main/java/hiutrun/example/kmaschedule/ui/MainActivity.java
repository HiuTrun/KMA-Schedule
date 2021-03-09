package hiutrun.example.kmaschedule.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.reactivestreams.Subscription;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private CompactCalendarView calendar;
    private RecyclerView rvEvent;
    private TextView tvName;
    private EventAdapter adapter;
    private ImageButton logout;
    private TextView textDay;
    private String s;
    public Date date = new Date();

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        s = getIntent().getStringExtra("model");
//        Log.d(TAG, "onCreate: "+s);

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
                textDay.setText("Tháng "+ DateFormat.format("MM/yyyy", firstDayOfNewMonth.getTime()));
            }
        });



    }

    private void init(){
//        Schedule schedule = (Schedule) ScheduleDatabase.getInstance(getApplicationContext()).getScheduleDao().getAllEvent();
//        List<Lesson> list;
//        if(schedule!=null){
//            list = schedule.getLessons();
//        }
        calendar = this.findViewById(R.id.calendarView);
        List<Schedule> schedules = ScheduleDatabase.getInstance(this).getScheduleDao()
                .getAllSchedule();
        for (Schedule item: schedules
        ) {
            Event event = new Event(Color.WHITE, Long.parseLong(item.getDate()), "Some extra data that I want to store.");
            calendar.addEvent(event);
        }
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setUseThreeLetterAbbreviation(true);
        textDay = this.findViewById(R.id.textDay);


        logout = (ImageButton)this.findViewById(R.id.exit);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog();
            }
        });
        rvEvent = (RecyclerView)this.findViewById(R.id.rvEvent);
        adapter = new EventAdapter(this,null);
        rvEvent.setLayoutManager(new LinearLayoutManager(this));
        tvName = this.findViewById(R.id.tvName);
        tvName.setText("Lương Trung Hiếu");
        rvEvent.setAdapter(adapter);
    }

    public void displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.logout_dialog, null);
        final Button positive = (Button) alertLayout.findViewById(R.id.btn_positive);
        final Button negative = (Button) alertLayout.findViewById(R.id.btn_negative);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            deleteDatabase("scheduledb.db");
            dialog.dismiss();
            finish();
            }
        });


        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}