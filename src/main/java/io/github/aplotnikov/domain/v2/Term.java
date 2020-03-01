package io.github.aplotnikov.domain.v2;

import static org.apache.commons.lang3.Validate.validState;

import java.util.Objects;

final class Term {

    private final byte value;

    private Term(byte value) {
        this.value = value;
    }

    static Term of(byte value) {
        validState(value > 0, "Term can not be less than one");
        validState(value <= 30, "Term can not be more than 30");

        return new Term(value);
    }

    byte getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Term term = (Term) other;
        return value == term.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Term{value=" + value + '}';
    }
}
