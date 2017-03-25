package com.github.vvinston.functional;

import java.util.Objects;

class Tuple<A, B> {

    private final A first;

    private final B second;

    Tuple(final A first, final B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
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
            final Tuple<?, ?> tuple = (Tuple<?, ?>) other;
            return Objects.equals(first, tuple.first) && Objects.equals(second, tuple.second);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Tuple{" + "first=" + first + ", second=" + second + '}';
    }

    public static <T, U> Tuple<T, U> of(final T first, final U second) {
        return new Tuple<>(first, second);
    }
}
