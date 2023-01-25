package ru.eltexstudy.courseapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseVideosData{
    private static List<String> webDev = new ArrayList<>(Arrays.asList(
            "YLUyVtHTGqw",
            "_NWqjokwZFI",
            "YVsB4EDFDbI",
            "ZICE4lDlH5I",
            "kc0ScJvmtd8",
            "heh1N3qltF8",
            "8KIh_eyy2Lo"));

    private List<String> ui = new ArrayList<>();
    private List<String> systemAdministration = new ArrayList<>();
    private List<String> webNative = new ArrayList<>();
    private List<String> tilda = new ArrayList<>();

    public static List<String> getWebDev() {
        return webDev;
    }
}
