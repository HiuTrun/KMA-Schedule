package hiutrun.example.kmaschedule.ui;

import androidx.lifecycle.ViewModel;

import hiutrun.example.kmaschedule.model.Student;
import hiutrun.example.kmaschedule.repository.ScheduleRepository;

public class ScheduleViewModel extends ViewModel {
    private ScheduleRepository repository;

    public ScheduleViewModel(ScheduleRepository repository) {
        this.repository = repository;
    }

    public void getTimetable(Student student){
        repository.getTimetable(student.getUsername(),student.getPassword(),"md5");
    }
}
