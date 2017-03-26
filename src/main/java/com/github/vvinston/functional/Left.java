package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

final class Left<L, R> implements Either<L, R>, Serializable {

    private static final long serialVersionUID = 1L;

    private final L payload;

    Left(@Nonnull final L payload) {
        this.payload = Objects.requireNonNull(payload);
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public Optional<L> getLeft() {
        return Optional.of(payload);
    }

    @Override
    public Optional<R> getRight() {
        return Optional.empty();
    }

    public L getPayload() {
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
            final Left<?, ?> left = (Left<?, ?>) other;
            return Objects.equals(payload, left.payload);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload);
    }

    @Override
    public String toString() {
        return "Left{payload=" + payload + '}';
    }
}
