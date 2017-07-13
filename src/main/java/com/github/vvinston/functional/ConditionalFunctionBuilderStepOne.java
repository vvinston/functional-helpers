package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ConditionalFunctionBuilderStepOne<INPUT> {

    private final Predicate<INPUT> predicate;

    public ConditionalFunctionBuilderStepOne(@Nonnull final Predicate<INPUT> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public <RESULT> ConditionalFunctionBuilderStepTwo<INPUT, RESULT> then(@Nonnull final Function<INPUT, RESULT> success) {
        final List<Tuple<Predicate<INPUT>, Function<INPUT, RESULT>>> cases = new LinkedList<>();
        cases.add(SimpleTuple.of(predicate, Objects.requireNonNull(success)));
        return new ConditionalFunctionBuilderStepTwo<>(cases);
    }
}
