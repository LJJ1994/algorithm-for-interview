package java_demo.common;

import java_demo.anno.Auth;

import java.lang.reflect.Method;

public class AnnoTest {
    @Auth(name = "yup", age = 25)
    public static void test() {

    }

    public static void getAnno() {
        Class<?> clz = Thread.currentThread().getClass();
        Method[] methods = clz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Auth.class)) {
                String name = method.getAnnotation(Auth.class).name();
                int age = method.getAnnotation(Auth.class).age();
                System.out.println("name: " + name + ", age: " + age);
            }
        }
    }

    public static void main(String[] args) {
        getAnno();
    }
}
