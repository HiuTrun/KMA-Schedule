package hiutrun.example.kmaschedule.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import hiutrun.example.kmaschedule.model.Lesson;

public class Converters {

    @TypeConverter
    public static List<Lesson> fromString(String value){
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Lesson> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
