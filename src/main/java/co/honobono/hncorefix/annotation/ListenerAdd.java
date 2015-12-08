package co.honobono.hncorefix.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.honobono.hncorefix.enums.ListenerType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ListenerAdd {
	ListenerType type() default ListenerType.DEFAULT;
}
