package com.shamel.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Student {

    private static long lastStudentId = 1;
    private final static Random random = new Random();

    private final long studentId;
    private final String countryCode;

    private final int yearEnrolled;
    private final int ageEnrolled;
    private final String gender;

    private final boolean programmingExperience;

    private final Map<String, CourseEngagement>  engagementMap = new HashMap<>();

    public Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender,
                   boolean programmingExperience, Course... courses) {

        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;
        studentId = lastStudentId ++;

        for(Course course : courses){
            addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
        }
    }

    private void addCourse(Course newCourse){

        addCourse(newCourse, LocalDate.now());

    }


    private void  addCourse(Course newCourse, LocalDate endDate ){
        engagementMap.put(newCourse.courseCode(),
                new CourseEngagement(newCourse, endDate, "Enrollment"));
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return Map.copyOf(engagementMap);
    }

    public long getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public String getGender() {
        return gender;
    }

    public boolean hasProgrammingExperience() {
        return programmingExperience;
    }

    public int getYearsSinceEnrolled(){
        return LocalDate.now().getYear() - yearEnrolled;
    }

    public int getAge(){
        return ageEnrolled +  getYearsSinceEnrolled();
    }




    public double getPercentComplete(String courseCode){

        var info = engagementMap.get(courseCode);
        return (info == null) ? 0 :  info.getPercentComplete();

    }



    @Override
    public String toString() {
        return "com.shamel.model.Student{" +
                "studentId=" + studentId +
                ", countryCode='" + countryCode + '\'' +
                ", yearEnrolled=" + yearEnrolled +
                ", ageEnrolled=" + ageEnrolled +
                ", gender='" + gender + '\'' +
                ", programmingExperience=" + programmingExperience +
                ", engagementMap=" + engagementMap +
                '}';
    }

    private static String getRandomVal(String... data){
        return data[random.nextInt(data.length)];
    }



    public void watchLecture(String courseCode, int lectureNumber, int month, int year){

        var activity = engagementMap.get(courseCode);
        if(activity != null){
            activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
        }
    }

    public int getMonthsSinceActive(String courseCode){

        CourseEngagement info = engagementMap.get(courseCode);
        return  info == null ? 0 : info.getMonthsSinceActive();

    }

    public int getMonthsSinceActive(){

        int inactiveMonth = (LocalDate.now().getYear() - 2014) * 12;

        for(String key : engagementMap.keySet()){
            inactiveMonth = Math.min(inactiveMonth, getMonthsSinceActive(key));
        }

        return inactiveMonth;
    }

    public static Student getRandomStudent(Course... courses){

        int maxYear = LocalDate.now().getYear();

        // constructing the randomized student:
        Student student = new Student(
                getRandomVal("AU", "CA", "CN", "GB", "IN", "UA", "US"),
                random.nextInt(2015, maxYear),
                random.nextInt(19,70),
                getRandomVal("M", "F", "U"),
                random.nextBoolean(),
                courses
        );

        for(Course c :  courses){
            int lecture = random.nextInt(1, c.lectureCount());
//            int year = random.nextInt(student.getYearEnrolled(), maxYear);
            int year = random.nextInt(student.getYearEnrolled(), maxYear);;
            int month = random.nextInt(1,13);

            student.watchLecture(c.courseCode(), lecture, month, year);
        }
        return student;
    }




}
