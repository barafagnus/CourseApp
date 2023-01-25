package ru.eltexstudy.courseapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ru.eltexstudy.courseapp.Adapter.AdapterMainCourses;
import ru.eltexstudy.courseapp.Adapter.AdapterMainNavigation;

public class HomeFragment extends Fragment implements RecyclerViewInterface{

    RecyclerView navRecycler;
    RecyclerView courseListRecycler;
    private LinkedList<CurrentNavItem> navList = new LinkedList<>();
    private LinkedList<Course> courseFilteredList = new LinkedList<>();
    private static LinkedList<Course> courseData;
    private ArrayList<Integer> prevPos = new ArrayList<>();
    private static LinkedList<User> userList = new LinkedList<>();
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManagerCourseList;
    AdapterMainNavigation adapterMainNavigation;
    AdapterMainCourses adapterMainCourses;
    private DrawerLayout drawerLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        courseData = new LinkedList<>();
        navList.add(new CurrentNavItem("Все", "#8D8D8D"));
        navList.add(new CurrentNavItem("Программирование", "#8D8D8D"));
        navList.add(new CurrentNavItem("Дизайн", "#8D8D8D"));
        navList.add(new CurrentNavItem("3D графика", "#8D8D8D"));
        navList.add(new CurrentNavItem("Маркетинг", "#8D8D8D"));
        navList.add(new CurrentNavItem("Фото", "#8D8D8D"));

        navRecycler = view.findViewById(R.id.navRecycler);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        setAdapterMainNavigation(navList);

        courseListRecycler = view.findViewById(R.id.courseListRecycler);
        courseData.add(new Course("IT", "Web разработка", "programming", "16 часов 30 минут", "Изучение основ верстки, связка CSS + HTML + JS", "Освой престижную профессию веб-разработчика. Создавай адаптивные веб-сайты или настраивай базу данных. За курс ты освоишь PHP, CSS, Javascript. Тебя ждут интересные задачи и простор для творчества"));
        courseData.add(new Course("Design", "UI/UX дизайн", "design", "14 часов 30 минут", "Методика разработки UI/UX Adobe AI + Figma", "Вы научитесь разрабатывать удобные сайты и приложения, адаптировать их под разные устройства. Изучите принципы векторной графики в Adobe Illustrator и среду Figma."));
        courseData.add(new Course("IT", "Сис. администрирование", "sysadmin", "14 часов 30 минут", "Администрирование Linux, настройка web-серверов", "За курсы вы научитесь создавать стабильные и безотказные инфраструктуры, займетесь настройкой сетей и мониторингом."));
        courseData.add(new Course("IT", "Нативная Web", "programming", "16 часов 30 минут", "JS TypeScript ReactNative", "Курс нацелен на изучение фреймворка ReactNative. Научитесь разрабатывать собственные адаптивные web-приложения."));
        courseData.add(new Course("Design", "Tilda", "design", "14 часов 30 минут", "Верстка сайта на Тильда", "За время курса, вы научитесь самостоятельно разрабатывать аккуратные и полезные сайты, лендинги, интернет-магазины на Тильде. После курса вы станете специалистом, который сможет решать сотни разных интересных задач для бизнеса. Квалифицированные дизайнеры на Тильде сегодня очень востребованы на фрилансе."));

        linearLayoutManagerCourseList = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setAdapterMainCourses(courseData);


        User user1 = new User("Вася Пупкин");
        user1.addActiveCourses(courseData.get(0).getCourseName());
        user1.addActiveCourses(courseData.get(1).getCourseName());
        user1.addActiveCourses(courseData.get(2).getCourseName());
        user1.addActiveCourses(courseData.get(3).getCourseName());

        User user2 = new User("Petya");
        user2.addActiveCourses(courseData.get(2).getCourseName());
        user2.addActiveCourses(courseData.get(3).getCourseName());
        userList.add(user1);
        userList.add(user2);

        return view;
    }

    @Override
    public void onItemNavigationClick(int position) {
        switch (position) {
            case (0):
                setAdapterMainCourses(courseData);
                break;
            case (1):
                courseDataFilter("IT");
                setAdapterMainCourses(courseFilteredList);
                break;
            case (2):
                courseDataFilter("Design");
                setAdapterMainCourses(courseFilteredList);
                break;
        }

        prevPos.add(position);
        if (prevPos.size() == 2) {
            System.out.println(prevPos);
            navList.set(prevPos.get(0), new CurrentNavItem(navList.get(prevPos.get(0)).getName(), "#8D8D8D"));
            navList.set(position, new CurrentNavItem(navList.get(position).getName(), "#313131"));
            prevPos.remove(0);
        }
        else {
            System.out.println(prevPos);
            navList.set(position, new CurrentNavItem(navList.get(position).getName(), "#313131"));
        }
        setAdapterMainNavigation(navList);
    }

    @Override
    public void onItemCourseClick(int position) {
        Intent intent = new Intent(getContext(), CourseInfoActivity.class);
        intent.putExtra("courseName", courseData.get(position).getCourseName());
        intent.putExtra("courseTime", courseData.get(position).getCourseTime());
        intent.putExtra("fullCourseDescription", courseData.get(position).getFullCourseDescription());
        startActivity(intent);
    }

    private void courseDataFilter(String category) {
        courseFilteredList.clear();
        for (int i = 0; i <= courseData.size() - 1; i++) {
            if (courseData.get(i).getCourseCategory().equals(category)) {
                courseFilteredList.add(courseData.get(i));
            }
        }
    }

    private void setAdapterMainCourses(LinkedList courseList) {
        adapterMainCourses = new AdapterMainCourses(getContext(), courseList, this);
        courseListRecycler.setLayoutManager(linearLayoutManagerCourseList);
        courseListRecycler.setAdapter(adapterMainCourses);
    }

    private void setAdapterMainNavigation(LinkedList navList) {
        adapterMainNavigation = new AdapterMainNavigation(navList, this);
        navRecycler.setLayoutManager(linearLayoutManager);
        navRecycler.setAdapter(adapterMainNavigation);
    }

    public static LinkedList<Course> getCourseData() {
        return courseData;
    }

    public static LinkedList<User> getUserList() {
        return userList;
    }
}