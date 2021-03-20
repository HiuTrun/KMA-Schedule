package hiutrun.example.kmaschedule.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.Calendar;
import java.util.Date;

import hiutrun.example.kmaschedule.R;
import hiutrun.example.kmaschedule.adapter.EventAdapter;
import hiutrun.example.kmaschedule.db.ScheduleDatabase;
import hiutrun.example.kmaschedule.model.Schedule;
import hiutrun.example.kmaschedule.repository.ScheduleRepository;
import hiutrun.example.kmaschedule.ui.MainActivity;
import hiutrun.example.kmaschedule.ui.ScheduleViewModel;
import hiutrun.example.kmaschedule.ui.ScheduleViewModelProviderFactory;


public class ScheduleFragment extends Fragment {
    private String TAG = ScheduleFragment.class.getSimpleName();
    private CompactCalendarView calendar;
    private RecyclerView rvEvent;
    private TextView tvName;
    private EventAdapter adapter;
    private ImageButton logout;
    private TextView textDay;
    private String s;
    public Date date = new Date();
    private ScheduleViewModel viewModel;
    private ScheduleRepository repository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_schedule, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        calendar = view.findViewById(R.id.calendarView);
        repository = new ScheduleRepository(ScheduleDatabase.getInstance(getContext()));
        ScheduleViewModelProviderFactory factory = new ScheduleViewModelProviderFactory(repository);
        viewModel = new ViewModelProvider(this,factory).get(ScheduleViewModel.class);

        viewModel.getAllEvents(calendar);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setUseThreeLetterAbbreviation(true);
        calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Long i = dateClicked.getTime() + 25200000;
                Log.d(TAG, "onDayClick: "+i);
                Log.d(TAG, "onDayClick: "+dateClicked.toString());
                viewModel.getTimetable(getContext(),i,adapter);
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textDay.setText("Tháng "+ DateFormat.format("MM/yyyy", firstDayOfNewMonth.getTime()));
            }
        });
        textDay = view.findViewById(R.id.textDay);


        logout = (ImageButton)view.findViewById(R.id.exit);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog();
            }
        });
        rvEvent = (RecyclerView)view.findViewById(R.id.rvEvent);
        adapter = new EventAdapter(getContext(),null);
        rvEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        tvName = view.findViewById(R.id.tvName);
        tvName.setText("Lương Trung Hiếu");
        rvEvent.setAdapter(adapter);
    }

    public void displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.logout_dialog, null);
        final Button positive = (Button) alertLayout.findViewById(R.id.btn_positive);
        final Button negative = (Button) alertLayout.findViewById(R.id.btn_negative);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();

//        positive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteDatabase("scheduledb.db");
//                dialog.dismiss();
//                finish();
//            }
//        });


        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}