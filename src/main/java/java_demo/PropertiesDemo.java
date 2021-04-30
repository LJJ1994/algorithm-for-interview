package java_demo;

import java.io.*;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        // 1. 通过文件流读取
//        InputStream in = new BufferedInputStream(new FileInputStream("src/main/resources/config.properties"));
//        properties.load(in);
//        System.out.println(properties.getProperty("name"));
//        System.out.println(properties.get("age"));

        // 2.通过PropertyResourceBundle获取
//        InputStream in = new BufferedInputStream(new FileInputStream("src/main/resources/config.properties"));
//        PropertyResourceBundle bundle = new PropertyResourceBundle(in);
//        System.out.println(bundle.getObject("name"));
//        System.out.println(bundle.getObject("age"));

        // 3.类加载
        InputStream inputStream =  PropertiesDemo.class.getResourceAsStream("/config/config.properties");
//        InputStream inputStream = PropertiesDemo.class.getClassLoader().getResourceAsStream("config/config.properties");
//        InputStream inputStream = ClassLoader.getSystemResourceAsStream("config/config.properties");
        properties.load(inputStream);
        System.out.println(properties.getProperty("name"));
        System.out.println(properties.getProperty("age"));
    }
}
