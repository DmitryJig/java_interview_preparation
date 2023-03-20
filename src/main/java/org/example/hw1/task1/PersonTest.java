package org.example.hw1.task1;

public class PersonTest {
    public static void main(String[] args) {
        Person person = Person.Builder.newBuilder().firstName("Dima").gender("Man").age(41).build();
        System.out.println(person); // Person{firstName='Dima', lastName='null', middleName='null', country='null', address='null', phone='null', age=41, gender='Man'}
    }
}
