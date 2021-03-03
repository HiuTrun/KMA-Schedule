package hiutrun.example.kmaschedule.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import hiutrun.example.kmaschedule.R;
import hiutrun.example.kmaschedule.db.ScheduleDatabase;
import hiutrun.example.kmaschedule.model.Student;
import hiutrun.example.kmaschedule.repository.ScheduleRepository;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ScheduleDatabase db = ScheduleDatabase.getInstance(this);
        ScheduleRepository repository = new ScheduleRepository(db);
        ScheduleViewModelProviderFactory factory = new ScheduleViewModelProviderFactory(repository);
        ScheduleViewModel viewModel = new ViewModelProvider(this, factory).get(ScheduleViewModel.class);
        initUI(viewModel);


    }

    private void initUI(ScheduleViewModel viewModel){
        edtUsername = this.findViewById(R.id.edtUsername);
        edtPassword = this.findViewById(R.id.edtPassword);
        btnLogin = this.findViewById(R.id.btnLogin);
        progressBar = this.findViewById(R.id.progressBar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUser(viewModel);
            }
        });
    }

    private void handleUser(ScheduleViewModel viewModel){
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        Student student = new Student(username,password);
        Log.d(TAG, "onClick: "+student.getUsername());
        viewModel.getTimetable(this,student);
    }

}