package mysite.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Auth {
    String value() default "user";
    boolean required() default true;
}
