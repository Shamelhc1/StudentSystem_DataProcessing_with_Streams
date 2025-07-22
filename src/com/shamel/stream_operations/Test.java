package com.shamel.stream_operations;

import com.shamel.model.Course;
import com.shamel.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        // creating three courses here again:

        Course pymc = new Course("PYMC", "Python Master class", 50);
        Course jmc = new Course("JMC", "Java Master class", 100);
        Course gameDesign = new Course("JMC", "Creating Games in Java");


        List<Student> students = Stream.generate(()->  Student.getRandomStudent(pymc,gameDesign,jmc))
                .limit(5000)
                .toList();

        // I'll Use the getPercentComplete method, to calculate the average percentage completed for
        // all students for just the Java Masterclass.

        var averagePercentComplete2 = students.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .average();

        System.out.println(averagePercentComplete2);

        //Using the average we will the students Set for these would be the students who've
        // completed more than three quarters of that average percentage.
        Double average = averagePercentComplete2.getAsDouble();

        // we will sort the above average students by time of enrollment;
        Set<Student> aboveAverageStudents = students.stream()
                .filter(student -> student.getPercentComplete("JMC") >= (1.25 * average))
                .collect(()-> new TreeSet<>(Comparator.comparing(Student::getYearEnrolled))
                        ,TreeSet::add
                        ,TreeSet::addAll);

    aboveAverageStudents.forEach(System.out::println);



    }
}
