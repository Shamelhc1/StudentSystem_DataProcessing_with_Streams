package com.shamel.stream_operations;

import com.shamel.model.Course;
import com.shamel.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {
    public static void main(String[] args) {

        // in here we will output the results of our queries into
        // lists and other containers using stream operations:

        Course pymc = new Course("PYMC", "Python Master class");
        Course jmc = new Course("JMC", "Java Master class");


        // Generating 1K random students and outputting the result into a list
        // container:

        List<Student> students =
                Stream.generate(()-> Student.getRandomStudent(jmc,pymc))
                        .limit(1000)
                        .toList();

        // let's extract the australian students:

        Set<Student> australianStudents = students.stream()
                .filter(s -> s.getCountryCode().equals("AU"))
                .collect(Collectors.toSet());

        System.out.println("# of Australian Students = "+ australianStudents.size());

        // extracting students under the age of 30:

        Set<Student> under30 = students.stream()
                .filter(s -> s.getAge()< 30 )
                .collect(Collectors.toSet());


        Set<Student> youngAustralians = new TreeSet<>(Comparator.comparing(Student::getAge));
        youngAustralians.addAll(australianStudents);

        // retaining all Australian students who are under the age of 30
        youngAustralians.retainAll(under30);

        System.out.println("\nList of Australian Students under the age of 30: ");
        youngAustralians.forEach(s-> System.out.println(s.getStudentId() + " "));
        System.out.println();


        // collecting Australian Students under 30 into
        // a TreeSet ordered by age

        Set<Student> youngAustralians2 = students.stream()
                .filter(s -> s.getAge()< 30 )
                .filter(s -> s.getCountryCode().equals("AU"))

                // the TreeSet will organize the students from oldest to youngest:
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getAge).reversed())

                        , TreeSet::add, TreeSet::addAll);

        System.out.println("TreeSet of Australian Students under 30 (oldeest to youngest)");
        youngAustralians2.forEach(s-> System.out.println("Student ID" + s.getStudentId() +
                " Student Age: " + s.getAge()));
        System.out.println();







    }

}
