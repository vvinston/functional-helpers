package com.github.vvinston.functional;

import java.util.List;

public class RerunnableException extends RuntimeException {

    private static final long serialVersionUID = 1;

    private final List<RuntimeException> causes;

    public RerunnableException(final String message, final List<RuntimeException> causes) {
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
