package hiutrun.example.kmaschedule.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import hiutrun.example.kmaschedule.repository.ScheduleRepository;

public class ScheduleViewModelProviderFactory implements ViewModelProvider.Factory {

    private ScheduleRepository repository;

    public ScheduleViewModelProviderFactory(ScheduleRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ScheduleViewModel(repository);
    }
}
