package hiutrun.example.kmaschedule.ui;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import hiutrun.example.kmaschedule.model.Student;
import hiutrun.example.kmaschedule.repository.ScheduleRepository;

public class ScheduleViewModel extends ViewModel {
    private ScheduleRepository repository;

    public ScheduleViewModel(ScheduleRepository repository) {
        this.repository = repository;
    }

    public void getTimetable(Context context, Student student){
        repository.getTimetable(context,student.getUsername(),student.getPassword(),"md5");
    }
}
