package ru.eltexstudy.courseapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import ru.eltexstudy.courseapp.Course;
import ru.eltexstudy.courseapp.CourseContent;
import ru.eltexstudy.courseapp.R;
import ru.eltexstudy.courseapp.RecyclerViewInterface;

public class UniversalRecyclerViewAdapter extends RecyclerView.Adapter<UniversalRecyclerViewAdapter.Holder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    LinkedList<Course> courseList;
    List<CourseContent> courseContent;
    String customViewType;
    int layoutName = R.layout.item_course_list;

    public UniversalRecyclerViewAdapter(Context context, LinkedList<Course> data, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.courseList = data;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    // Костыльный конструктор
    public UniversalRecyclerViewAdapter(Context context, List<CourseContent> data, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.courseContent = data;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public UniversalRecyclerViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (customViewType == "CourseActivity") {
            this.layoutName = R.layout.item_course_content;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutName, null, false);
        return new UniversalRecyclerViewAdapter.Holder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversalRecyclerViewAdapter.Holder holder, int position) {
        if (customViewType == "CourseActivity") {
            int imageId = context.getResources().getIdentifier("btn_play2", "drawable", context.getPackageName());
            holder.playVideo.setImageResource(imageId);
            holder.title.setText(courseContent.get(position).getTitle());
            holder.duration.setText(courseContent.get(position).getDuration());
        }

        else {
            int imageId = context.getResources().getIdentifier("course_" + courseList.get(position).getCourseLogo(), "drawable", context.getPackageName());
            holder.courseLogo.setImageResource(imageId);
            //holder.courseCard.setCardBackgroundColor(Color.parseColor(courseList.get(position).getCourseBg()));
            holder.courseName.setText(courseList.get(position).getCourseName());
            holder.courseDescription.setText(courseList.get(position).getCourseDescription());
            holder.courseTime.setText(courseList.get(position).getCourseTime());
        }
    }

    @Override
    public int getItemCount() {
        if (customViewType == "CourseActivity") {
            return courseContent.size();
        }
        else {
            return courseList.size();
        }
    }

    public void setCustomViewType(String customViewType) {
        this.customViewType = customViewType;
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView courseLogo;
        CardView courseCard;
        TextView courseName;
        TextView courseDescription;
        TextView courseTime;

        ImageView playVideo;
        TextView duration;
        TextView title;
        TextView countViews;
        TextView countLikes;
        TextView videoDuration;

        public Holder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            if (customViewType == "CourseActivity") {
                playVideo = itemView.findViewById(R.id.playVideo);
                duration = itemView.findViewById(R.id.duration);
                title = itemView.findViewById(R.id.title);
                countViews = itemView.findViewById(R.id.countViews);
                countLikes = itemView.findViewById(R.id.countLikes);
                videoDuration = itemView.findViewById(R.id.videoDuration);
            }

            else {
                courseName = itemView.findViewById(R.id.courseName);
                courseLogo = itemView.findViewById(R.id.courseLogo);
                courseCard = itemView.findViewById(R.id.courseCard);
                courseTime = itemView.findViewById(R.id.courseTime);
                courseDescription = itemView.findViewById(R.id.courseDescription);
            }

            itemView.setOnClickListener(view -> {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemCourseClick(pos);
                    }
                }
            });
        }
    }
}
