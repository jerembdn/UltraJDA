package fr.the3dx900.ultrajda.command;

import net.dv8tion.jda.api.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandHandler {
    String command();
    String[] description() default {""};
    String[] usages() default {};
    String[] aliases() default {};
    Permission[] permission() default {};
    String[] channels() default {};
    String[] guild() default {};
    boolean deleteCommandAfter() default true;
}
