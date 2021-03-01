package hiutrun.example.kmaschedule.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import hiutrun.example.kmaschedule.R;
import hiutrun.example.kmaschedule.api.ApiService;
import hiutrun.example.kmaschedule.api.RetrofitInstance;
import hiutrun.example.kmaschedule.model.Model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiService service = RetrofitInstance
                .getInstance()
                .create(ApiService.class);
        Call<Model> call = service.getTimetable("CT040320","17/07/2001","md5");
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }



}