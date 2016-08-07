package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Optional;

public interface Either<L, R> {

    boolean isLeft();

    boolean isRight();

    Optional<L> getLeft();

    Optional<R> getRight();

    static <L, R> Either<L, R> left(@Nonnull final L payload) {
        return new Left<>(payload);
    }

    static <L, R> Either<L, R> right(@Nonnull final R payload) {
        return new Right<>(payload);
    }
}
