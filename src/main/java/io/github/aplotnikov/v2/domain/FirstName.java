package io.github.aplotnikov.v2.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

        return new EqualsBuilder()
            .append(name, firstName.name)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(name)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "FirstName{name='" + name + "\'}";
    }
}
