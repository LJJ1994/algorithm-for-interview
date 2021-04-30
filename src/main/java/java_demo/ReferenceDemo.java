package java_demo;

import java.io.Serializable;

public class ReferenceDemo {
    public static void test(User user) {
        user.setName("new Name");
        System.out.println("in pass: " + user);
    }

    public static void test1(int a) {
        a += 1;
        System.out.println("in pass a: " + a);
    }

    public static void test2(User user) {
        user = new User();
        user.setName("New Name");
        user.setAge(1999);
        System.out.println("in pass user2: " + user);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setAge(26);
        user.setName("LJJ");
        System.out.println("before pass, in main: " + user);
//        test(user);
//        System.out.println("after pass, in main: " + user);
//
//        int a = 2;
//        test1(a);
//        System.out.println("in main a: " + a);
        test2(user);
        System.out.println("after pass, in main: " + user);
    }

    private static class User implements Serializable {
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
        public User() {}

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
