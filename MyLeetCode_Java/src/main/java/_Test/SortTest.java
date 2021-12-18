package _Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SortTest {

    public static class Person {
        public int age;
        public String name;

        public Person(int a, String n) {
            age = a;
            name = n;
        }
    }

    public static void main(String[] args) {

        List<Person> plist = new ArrayList<>();
        plist.add(new Person(15, "Ming"));
        plist.add(new Person(34, "Jing"));
        plist.add(new Person(7, "Tom"));
        plist.add(new Person(12, "Jerry"));
        Collections.sort(plist, (Person o1, Person o2) -> o1.age - o2.age);
        for (Person person : plist) {
            System.out.printf("Age:%d, Name:%s.\n", person.age, person.name);
        }

    }
}
