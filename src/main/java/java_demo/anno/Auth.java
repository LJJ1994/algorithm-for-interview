package java_demo.anno;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Auth{
    String name() default "ljj";
    int age() default 26;
}
