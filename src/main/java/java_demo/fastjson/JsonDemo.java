package java_demo.fastjson;

import com.alibaba.fastjson.JSON;

public class JsonDemo {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("ljj");
        person.setPhone(123456);
        person.setAddress("广州");
        System.out.println("序列化...................");
        String serialize = serialize(person);
        System.out.println("反序列化..................");
        Person deserialize = deserialize(serialize);
    }

    private static String serialize(Person person) {
        String s = JSON.toJSONString(person);
        System.out.println("serialize: " + s);
        return s;
    }

    private static Person deserialize(String s) {
        Person person = JSON.parseObject(s, Person.class);
        System.out.println("deserialize: " + person);
        return person;
    }
}
