package com.shamel.model;

import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Master class");
        Course jmc = new Course("JMC", "Java Master class");


        // we will generate a stream source of random student data set
        // using the getRandomStudent factory method on Student:

        var students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(1000);

        // how many males/females student are in the group?
        var maleStudents =  students.filter(student -> student.getGender().startsWith("M"))
                                    .count();
        System.out.println("Male or female students: "+ maleStudents);

        // How many students fall into the age range of 30 - 60 years:
        // Note the age of the student will be equal to or greater than the age at enrolment
        var workingAgeStudents = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(1000)
                .filter(student -> student.getAge() >= 30 && student.getAge() <= 60 )
                .count();

        System.out.println("\nStudents within the age range of 30 - 60 years: "+ workingAgeStudents);

        // Using the summaryStatistics on the student's age, to get a better idea of how old the student population is.
        // this will show us age treands between the students (average, min, max, etc)
        var ageSummary = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(100)
                .mapToInt(Student::getAge)
                .summaryStatistics();

        System.out.println("\nStudent age summary:\n" + ageSummary);

        // What countries are the students from?  let's a distinct list of the country codes.
        var distinctCountries = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(100)
                .map(Student::getCountryCode)
                .distinct()
                .toList();

        System.out.println("\nThe distinct list of Student's countries:\n" + distinctCountries);


        // Are there students that are still active (within last year) and have been enrolled for more than 7 years?
        var unicornStudent = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(100000)
                .anyMatch(student -> student.getYearsSinceEnrolled() >= 7 &&
                                    student.getMonthsSinceActive() < 12);
        System.out.println("\nAre there students that are still active and have been enrolled for more than 7 years?\n"
                +unicornStudent);

    }

}