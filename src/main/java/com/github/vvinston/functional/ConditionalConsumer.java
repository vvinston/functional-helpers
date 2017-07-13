package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

final class ConditionalConsumer<INPUT> implements Consumer<INPUT> {

    private final List<Tuple<Predicate<INPUT>, Consumer<INPUT>>> cases = new LinkedList<>();
    private final Consumer<INPUT> otherwise;

    ConditionalConsumer(
            @Nonnull final List<Tuple<Predicate<INPUT>, Consumer<INPUT>>> cases,
            @Nonnull final Consumer<INPUT> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public void accept(@Nullable final INPUT input) {
        for (final Tuple<Predicate<INPUT>, Consumer<INPUT>> kase : cases) {
            if (kase.getFirst().test(input)) {
                kase.getSecond().accept(input);
                return;
            }
        }

        otherwise.accept(input);
    }
}
