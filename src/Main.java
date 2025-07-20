import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Master class");
        Course jmc = new Course("JMC", "Java Master class");

        Stream.generate(()-> Student.getRandomStudent(jmc,pymc))
                .limit(10)
            .forEach(System.out::println);




    }

}