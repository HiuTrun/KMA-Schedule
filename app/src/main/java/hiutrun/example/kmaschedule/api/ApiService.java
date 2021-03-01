package hiutrun.example.kmaschedule.api;

import hiutrun.example.kmaschedule.model.Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/kma?")
    Call<Model> getTimetable(
            @Query("username") String username,
            @Query("password") String password,
            @Query("passwordhash") String passwordhash
    );
}
