package co.honobono.hncorefix.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.honobono.hncorefix.enums.CommandType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AddCommand {
	String command();
	String[] alias() default { };
	String description();
	String permission();
	String permissionmessage() default "You don't have permission.";
	String usage() default "Not added";
	CommandType type() default CommandType.INDIRECTION;
}
