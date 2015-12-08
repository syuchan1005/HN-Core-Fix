package co.honobono.hncorefix.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AddCommand {
	String[] command();
	String description();
	String permission();
	String permissionmessage() default "You don't have permission.";
	String usage() default "";
}
