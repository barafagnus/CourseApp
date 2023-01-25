package ru.eltexstudy.courseapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import ru.eltexstudy.courseapp.Course;
import ru.eltexstudy.courseapp.R;
import ru.eltexstudy.courseapp.RecyclerViewInterface;

public class AdapterMainCourses extends RecyclerView.Adapter<AdapterMainCourses.Holder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    LinkedList<Course> courseList;

    public AdapterMainCourses(Context context, LinkedList<Course> data, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.courseList = data;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public AdapterMainCourses.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_list, null, false);
        return new AdapterMainCourses.Holder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMainCourses.Holder holder, int position) {
        int imageId = context.getResources().getIdentifier("course_" + courseList.get(position).getCourseLogo(), "drawable", context.getPackageName());
        holder.courseLogo.setImageResource(imageId);
        //holder.courseCard.setCardBackgroundColor(Color.parseColor(courseList.get(position).getCourseBg()));
        holder.courseName.setText(courseList.get(position).getCourseName());
        holder.courseDescription.setText(courseList.get(position).getCourseDescription());
        holder.courseTime.setText(courseList.get(position).getCourseTime());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView courseLogo;
        CardView courseCard;
        TextView courseName;
        TextView courseDescription;
        TextView courseTime;

        public Holder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            courseName = itemView.findViewById(R.id.courseName);
            courseLogo = itemView.findViewById(R.id.courseLogo);
            courseCard = itemView.findViewById(R.id.courseCard);
            courseTime = itemView.findViewById(R.id.courseTime);
            courseDescription = itemView.findViewById(R.id.courseDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemCourseClick(pos);
                        }
                    }
                }
            });
        }
    }
}
