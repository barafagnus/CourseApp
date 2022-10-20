package ru.vysokov.coursesapp.adapter;
import ru.vysokov.coursesapp.CoursePage;
import ru.vysokov.coursesapp.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.vysokov.coursesapp.model.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        holder.courseBg.setBackgroundColor(Color.parseColor(courses.get(position).getBgcolor()));
        int imageId = context.getResources().getIdentifier("ic_" + courses.get(position).getImg(), "drawable", context.getPackageName());
        holder.courseImage.setImageResource(imageId);
        holder.courseName.setText(courses.get(position).getCoursename());
        holder.courseInfo.setText(courses.get(position).getCourseinfo());
        holder.countCourses.setText(courses.get(position).getCountcourses());
        holder.timeCourses.setText(courses.get(position).getTimecourses());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CoursePage.class);
                  context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder {
        LinearLayout courseBg;
        ImageView courseImage;
        TextView courseName, courseInfo, countCourses, timeCourses;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseBg = itemView.findViewById(R.id.courseBg);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseName = itemView.findViewById(R.id.courseName);
            courseInfo = itemView.findViewById(R.id.courseInfo);
            countCourses = itemView.findViewById(R.id.countCourses);
            timeCourses = itemView.findViewById(R.id.timeCourses);


        }
    }
}
