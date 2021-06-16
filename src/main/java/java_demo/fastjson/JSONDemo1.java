package java_demo.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class JSONDemo1 {
    private static String serialize(PersonComplex person) {
        String s = JSON.toJSONString(person);
        System.out.println("serialize: " + s);
        return s;
    }

    private static PersonComplex deserialize(String s) {
        PersonComplex personComplex = JSON.parseObject(s, PersonComplex.class);
        System.out.println("deserialize: " + personComplex);
        return personComplex;
    }

    public static void main(String[] args) {
        PersonComplex personComplex = new PersonComplex();
        personComplex.setName("ljj");
        personComplex.setAddress("广州");
        personComplex.setIds(Arrays.asList(1, 2, 3, 4));
        personComplex.setPhone(12345);

        Friend alice = new Friend();
        alice.setColor("red");
        Friend jack = new Friend();
        jack.setColor("yellow");
        personComplex.setFriends(Arrays.asList(alice, jack));

        System.out.println("序列化..................");
        String serialize = serialize(personComplex);
        System.out.println("反序列化....................");
        PersonComplex deserialize = deserialize(serialize);

    }
}
