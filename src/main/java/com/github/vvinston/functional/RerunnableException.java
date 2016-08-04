package com.github.vvinston.functional;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.List;

public class RerunnableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final List<RuntimeException> causes;

    public RerunnableException(@Nonnull final String message, @Nonnull final List<RuntimeException> causes) {
        super(Preconditions.checkNotNull(message));
        this.causes = Preconditions.checkNotNull(causes);
    }

    public List<RuntimeException> getCauses() {
        return causes;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
