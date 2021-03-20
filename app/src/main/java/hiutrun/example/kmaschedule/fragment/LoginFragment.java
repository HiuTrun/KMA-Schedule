package hiutrun.example.kmaschedule.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.File;

import hiutrun.example.kmaschedule.R;
import hiutrun.example.kmaschedule.db.ScheduleDatabase;
import hiutrun.example.kmaschedule.model.Student;
import hiutrun.example.kmaschedule.repository.ScheduleRepository;
import hiutrun.example.kmaschedule.ui.ScheduleViewModel;
import hiutrun.example.kmaschedule.ui.ScheduleViewModelProviderFactory;

public class LoginFragment extends Fragment {
    private static final String TAG = LoginFragment.class.getSimpleName();
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private ScheduleViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScheduleDatabase db = ScheduleDatabase.getInstance(getContext());
        ScheduleRepository repository = new ScheduleRepository(db);
        ScheduleViewModelProviderFactory factory = new ScheduleViewModelProviderFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(ScheduleViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        initUI(view);
        return view;

    }

    private void initUI( View view){

//        edtPassword = view.findViewById(R.id.edtPassword);
//        btnLogin = view.findViewById(R.id.btnLogin);
//        progressBar = view.findViewById(R.id.progressBar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUser(viewModel);
            }
        });
    }

    private void handleUser(ScheduleViewModel viewModel){
        String username = edtUsername.getText().toString().toUpperCase();
        String password = edtPassword.getText().toString();
        Student student = new Student(username,password);
        Log.d(TAG, "onClick: "+student.getUsername());
        viewModel.signIn(getContext(),student);
    }

    private boolean checkDatabase(Context context, String dbName){
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}