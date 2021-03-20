package hiutrun.example.kmaschedule.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import hiutrun.example.kmaschedule.R;
import hiutrun.example.kmaschedule.model.Lesson;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private Context context;
    private List<Lesson> lessons;

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        notifyDataSetChanged();
    }

    public EventAdapter(Context context, List<Lesson> lessons) {
        this.context = context;
        this.lessons = lessons;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("Event Adapter", "onBindViewHolder: "+lessons.isEmpty());
        if(lessons!=null){
            Lesson lesson = lessons.get(position);
            holder.tvTitle.setText(lesson.getSubjectName());
            holder.tvLocation.setText(lesson.getAddress());
            switch (lesson.getLesson()){
                case "1,2,3":
                    holder.tvTime.setText("7:00 - 9:25");
                    break;
                case "4,5,6":
                    holder.tvTime.setText("9:30 - 12:00");
                    break;
                case "7,8,9":
                    holder.tvTime.setText("12:30 - 14:55");
                    break;
                case "10,11,12":
                    holder.tvTime.setText("15:00 - 16:55");
                    break;
                case "13,14,15,16":
                    holder.tvTime.setText("18:00 - 21:15");
                    break;
                default:
                    holder.tvTime.setText("null");
            }
        }else {
            Log.d("Event Adapter", "onBindViewHolder: here");
            //holder.tvStatus.setText("Bạn không có lịch học vào hôm nay!");
            //holder.tvStatus.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public int getItemCount() {
       // Log.d("TAG", "getItemCount: "+lessons.size());
        if(lessons == null)
            return 0;
        else return lessons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvLocation;
        private TextView tvTime;
        private TextView tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            //tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
