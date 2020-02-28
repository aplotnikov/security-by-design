package io.github.aplotnikov.domain.v2;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.Objects;

final class FirstName {

    private final String name;

    private FirstName(String name) {
        this.name = name;
    }

    static FirstName of(String value) {
        checkNotNull(value, "First name can not be null");
        checkState(!value.isBlank(), "First name can not be empty or blank");
        checkState(value.length() <= 30, "First name can not contain more than 30 letters");
        checkState(value.matches("[A-z]+"), "First name can contain only latin letters");

        return new FirstName(value);
    }

    String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        FirstName firstName = (FirstName) other;
        return Objects.equals(name, firstName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "FirstName{name='" + name + "\'}";
    }
}
