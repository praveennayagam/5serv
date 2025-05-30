package annotation;



import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FixedFieldAnnotations {
    int length();
    String pattern() default "";
    boolean required() default true;
    String allowedValues() default "";
    String format() default "";
}