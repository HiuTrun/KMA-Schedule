package hiutrun.example.kmaschedule.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import hiutrun.example.kmaschedule.R;
import hiutrun.example.kmaschedule.api.ApiService;
import hiutrun.example.kmaschedule.api.RetrofitInstance;
import hiutrun.example.kmaschedule.model.Model;
import hiutrun.example.kmaschedule.model.Student;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = getIntent().getStringExtra("model");

        Log.d(TAG, "onCreate: "+s);
    }



}