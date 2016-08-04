package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;

public class RerunnableException extends RuntimeException {

    private static final long serialVersionUID = 1;

    private final List<RuntimeException> causes;

    public RerunnableException(@Nonnull final String message, @Nonnull final List<RuntimeException> causes) {
        super(message);
        this.causes = causes;
    }

    public List<RuntimeException> getCauses() {
        return causes;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
