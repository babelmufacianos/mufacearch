package muface.arch.command;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArqUseCaseDefinition {
    String value();
    ArqUseCaseType type();
}
