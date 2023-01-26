package ru.eltexstudy.courseapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import ru.eltexstudy.courseapp.Adapter.UniversalRecyclerViewAdapter;
import ru.eltexstudy.courseapp.Adapter.AdapterMainNavigation;

public class HomeFragment extends Fragment implements RecyclerViewInterface{

    RecyclerView navRecycler;
    RecyclerView courseListRecycler;
    private LinkedList<CurrentNavItem> navList = new LinkedList<>();
    private LinkedList<Course> courseFilteredList = new LinkedList<>();
    private static LinkedList<Course> courseData;
    private ArrayList<Integer> prevPos = new ArrayList<>();
    private static ArrayList<User> userList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManagerCourseList;
    AdapterMainNavigation adapterMainNavigation;
    UniversalRecyclerViewAdapter universalRecyclerViewAdapter;
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

        courseData.add(new Course(
            "IT",
            "Web разработка",
            "programming",
            "16 часов 30 минут",
            "Изучение основ верстки, связка CSS + HTML + JS",
            "Освой престижную профессию веб-разработчика. Создавай адаптивные веб-сайты или настраивай базу данных. За курс ты освоишь PHP, CSS, Javascript. Тебя ждут интересные задачи и простор для творчества",
                new ArrayList<>(Arrays.asList(
                        "YLUyVtHTGqw",
                        "_NWqjokwZFI",
                        "YVsB4EDFDbI",
                        "ZICE4lDlH5I",
                        "kc0ScJvmtd8",
                        "heh1N3qltF8",
                        "8KIh_eyy2Lo"))));

        courseData.add(new Course(
                "Design",
                "UI/UX дизайн",
                "design",
                "14 часов 30 минут",
                "Методика разработки UI/UX Adobe AI + Figma",
                "Вы научитесь разрабатывать удобные сайты и приложения, адаптировать их под разные устройства. Изучите принципы векторной графики в Adobe Illustrator и среду Figma.",
                new ArrayList<>(Arrays.asList(
                        "XFKiaElHlmU",
                        "QISEeyXSULg",
                        "XtSGYbnJD0M",
                        "Adkq3T_VUxQ",
                        "WsNCwTprBIQ",
                        "E4EaayPKYvg",
                        "hUUAGBqEJnM"))));

        courseData.add(new Course(
                "IT",
                "Сис. администрирование",
                "sysadmin",
                "14 часов 30 минут",
                "Администрирование Linux, настройка web-серверов",
                "За курсы вы научитесь создавать стабильные и безотказные инфраструктуры, займетесь настройкой сетей и мониторингом.",
                new ArrayList<>(Arrays.asList(
                        "Z-a7MNStFQs",
                        "wL-axMRQky8",
                        "uEv14oIwUBs",
                        "cgN3-Z_w2uA",
                        "I_AB7R36q6M"))));

        courseData.add(new Course(
                "IT",
                "Нативная Web разработка",
                "programming",
                "16 часов 30 минут",
                "JS TypeScript ReactNative",
                "Курс нацелен на изучение фреймворка ReactNative. Научитесь разрабатывать собственные адаптивные web-приложения.",
                new ArrayList<>(Arrays.asList(
                        "j9nVVIq9eSQ",
                        "YjuaFefiQe4",
                        "2d_pn5oKrRg",
                        "Q338MkfbBEQ",
                        "hfSFWNzifp4",
                        "t7rzCbRBqLU"))));

        courseData.add(new Course(
                "Design",
                "Tilda",
                "design",
                "14 часов 30 минут",
                "Верстка сайта на Тильда",
                "За время курса, вы научитесь самостоятельно разрабатывать аккуратные и полезные сайты, лендинги, интернет-магазины на Тильде. После курса вы станете специалистом, который сможет решать сотни разных интересных задач для бизнеса. Квалифицированные дизайнеры на Тильде сегодня очень востребованы на фрилансе.",
                new ArrayList<>(Arrays.asList(
                        "YOT0AqIp6VA",
                        "DF0NqAwV0NM",
                        "m8nm4RuBRoQ",
                        "JHqsnWaT9Sw",
                        "irYU2XBFQjU"))));

        linearLayoutManagerCourseList = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setAdapterMainCourses(courseData);

        // Добавление пользователя
        if (userList.isEmpty()) {
            User user1 = new User("Сергей", "barafagnus@gmail.com");
            userList.add(user1);
        }
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
            navList.set(prevPos.get(0), new CurrentNavItem(navList.get(prevPos.get(0)).getName(), "#8D8D8D"));
            navList.set(position, new CurrentNavItem(navList.get(position).getName(), "#313131"));
            prevPos.remove(0);
        }
        else {
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
        intent.putExtra("videoId", courseData.get(position).getCourseVideoId().get(position));
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

    private void setAdapterMainCourses(LinkedList<Course> courseList) {
        universalRecyclerViewAdapter = new UniversalRecyclerViewAdapter(getContext(), courseList, this);

        courseListRecycler.setLayoutManager(linearLayoutManagerCourseList);
        courseListRecycler.setAdapter(universalRecyclerViewAdapter);
    }

    private void setAdapterMainNavigation(LinkedList<CurrentNavItem> navList) {
        adapterMainNavigation = new AdapterMainNavigation(navList, this);
        navRecycler.setLayoutManager(linearLayoutManager);
        navRecycler.setAdapter(adapterMainNavigation);
    }

    public static LinkedList<Course> getCourseData() {
        return courseData;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static void setUserList(ArrayList<User> userList) {
        HomeFragment.userList = userList;
    }
}