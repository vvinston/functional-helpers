package com.github.vvinston.functional;

import java.util.Objects;

public final class SimpleTuple<FIRST, SECOND> implements Tuple<FIRST, SECOND> {
    private final FIRST first;

    private final SECOND second;

    private SimpleTuple(final FIRST first, final SECOND second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public FIRST getFirst() {
        return first;
    }

    @Override
    public SECOND getSecond() {
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
            final SimpleTuple<?, ?> tuple = (SimpleTuple<?, ?>) other;
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
        return "SimpleTuple{" + "first=" + first + ", second=" + second + '}';
    }

    public static <FIRST, SECOND> Tuple<FIRST, SECOND> of(final FIRST first, final SECOND second) {
        return new SimpleTuple<>(first, second);
    }
}
