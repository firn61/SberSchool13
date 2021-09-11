package edu.sber.lect8.annotatons;

import edu.sber.lect8.enums.CacheType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cached {

    CacheType cacheType() default CacheType.IN_MEMORY;
    String fileNamePrefix() default "";
    boolean zip() default false;
    Class[] identityBy() default Class.class;
    int cutOff() default Integer.MAX_VALUE;

}
