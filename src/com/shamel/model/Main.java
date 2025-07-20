package com.shamel.model;

import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Master class");
        Course jmc = new Course("JMC", "Java Master class");

        // Using the factory method in Student class, we can generate a random
        // data set of students, which will be Stream source for our pipeline

        // How many Male, and female students are in the group?

        var maleCount = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(100)
                .peek(System.out::println)
                .filter(student -> student.getGender().equalsIgnoreCase("M"))
                .count();
        System.out.println(maleCount);

        System.out.println("-".repeat(20));

        // How many students fall in the age range of 30 to 60 years old inclusive?
        // the current age is not age enrolled field

        var workingAge = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(10)
                .filter(student -> student.getAge() >= 30 && student.getAge() <= 60 )
                .peek(System.out::println)
                .count();
        System.out.println(workingAge);

        // let's get summary statistics on our students to see the
        // statistical general trends:


        //Student's age summary stats:
        var studentAgeStats = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(100)
                .mapToInt(Student::getAge)
                .summaryStatistics();

        System.out.println(studentAgeStats);


        // what countries are the students from in our data:
        var countryStats = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(100)
                .map(Student::getCountryCode)
                .distinct();

        // this will print the distinct counties
        countryStats.forEach(System.out::println);

        // Are there students that are still active and have been
        // enrolled for more than 7 years?
        var unicornStudent = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(10000)
                .anyMatch( s -> s.getYearsSinceEnrolled() > 7 && s.getMonthsSinceActive() < 20);
        System.out.println(unicornStudent);



        var unicornStudentprint = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(10000)
                .filter( s -> s.getYearsSinceEnrolled() > 7 && s.getMonthsSinceActive() < 20)
                .limit(5);

        unicornStudentprint.forEach(System.out::println);






    }

}