package ru.eltexstudy.courseapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ru.eltexstudy.courseapp.Adapter.AdapterMainCourses;

public class PersonalAccountFragment extends Fragment implements RecyclerViewInterface {
    LinearLayoutManager linearLayoutManagerCourseList;
    AdapterMainCourses adapterMainCourses;
    RecyclerView courseListRecycler;
    LinkedList<Course> courseData = HomeFragment.getCourseData();
    private List<User> userList = HomeFragment.getUserList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_account, container, false);
        courseListRecycler = view.findViewById(R.id.courseListRecycler);
        linearLayoutManagerCourseList = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setAdapterMainCourses(userListCourseFilter(0, userList, courseData));

        return view;
    }

    private void setAdapterMainCourses(LinkedList courseList) {
        adapterMainCourses = new AdapterMainCourses(getContext(), courseList, this);
        courseListRecycler.setLayoutManager(linearLayoutManagerCourseList);
        courseListRecycler.setAdapter(adapterMainCourses);
    }

    @Override
    public void onItemNavigationClick(int position) {

    }

    @Override
    public void onItemCourseClick(int position) {
        Intent courseActivity = new Intent(getContext(), CourseActivity.class);
        startActivity(courseActivity);
    }

    private LinkedList<Course> userListCourseFilter(int userId, List<User> userList, LinkedList<Course> courseData) {
        List<String> activeCourses = new ArrayList<>();
        List<String> listMask = new ArrayList<>();
        LinkedList<Course> filteredCourseList = new LinkedList<>();
        userList.get(userId).getActiveCourses().forEach(course -> {
            activeCourses.add(course);
        });

        courseData.stream().map(c -> c.getCourseName()).forEach(course -> {
            if (activeCourses.contains(course)) {
                listMask.add("1");
            }
            else {
                listMask.add("0");
            }
        });

        courseData.stream().filter((c) -> listMask.get(courseData.indexOf(c)) == "1").forEach(element -> {
            filteredCourseList.add(element);
        });

        return filteredCourseList;
        }
}
