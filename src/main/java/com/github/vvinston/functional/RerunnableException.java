package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public final class RerunnableException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final List<RuntimeException> causes;

    RerunnableException(@Nonnull final String message, @Nonnull final List<RuntimeException> causes) {
        super(Objects.requireNonNull(message));
        this.causes = Objects.requireNonNull(causes);
    }

    public List<RuntimeException> getCauses() {
        return causes;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
