package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

final class Right<LEFT, RIGHT> implements Either<LEFT, RIGHT>, Serializable {

    private static final long serialVersionUID = 1L;

    private final RIGHT payload;

    Right(@Nonnull final RIGHT payload) {
        this.payload = Objects.requireNonNull(payload);
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public Optional<LEFT> getLeft() {
        return Optional.empty();
    }

    @Override
    public Optional<RIGHT> getRight() {
        return Optional.of(payload);
    }

    public RIGHT getPayload() {
        return payload;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (getClass() == other.getClass()) {
            final Right<?, ?> right = (Right<?, ?>) other;
            return Objects.equals(payload, right.payload);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload);
    }

    @Override
    public String toString() {
        return "Right{payload=" + payload + '}';
    }
}
