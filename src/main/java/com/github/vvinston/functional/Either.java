package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Optional;

public interface Either<LEFT, RIGHT> {

    boolean isLeft();

    boolean isRight();

    Optional<LEFT> getLeft();

    Optional<RIGHT> getRight();

    static <LEFT, RIGHT> Either<LEFT, RIGHT> left(@Nonnull final LEFT payload) {
        return new Left<>(payload);
    }

    static <LEFT, RIGHT> Either<LEFT, RIGHT> right(@Nonnull final RIGHT payload) {
        return new Right<>(payload);
    }
}
