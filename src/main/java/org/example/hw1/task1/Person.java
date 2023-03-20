package org.example.hw1.task1;

/**
 * ЗАДАНИЕ 1
 * Создать builder для класса Person со следующими полями:
 * String firstName, String lastName, String middleName,
 * String country, String address, String phone, int age, String gender.
 */
public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private Person(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        middleName = builder.middleName;
        country = builder.country;
        address = builder.address;
        phone = builder.phone;
        age = builder.age;
        gender = builder.gender;
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String middleName;
        private String country;
        private String address;
        private String phone;
        private int age;
        private String gender;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder middleName(String val) {
            middleName = val;
            return this;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder gender(String val) {
            gender = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", middleName='" + middleName + '\'' + ", country='" + country + '\'' + ", " +
                "address='" + address + '\'' + ", phone='" + phone + '\'' + ", age=" + age + ", gender='" + gender + '\'' + '}';
    }
}
