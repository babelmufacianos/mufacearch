package muface.arch.controller;

import muface.arch.command.usecase.ArqUseCaseType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArqUseCaseDefinition {
    String value();
    ArqUseCaseType type();
}

