package io.github.aplotnikov.v2.domain;

import static org.apache.commons.lang3.Validate.matchesPattern;
import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.Validate.validState;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

final class SecondName {

    private final String name;

    SecondName(String name) {
        this.name = name;
    }

    static SecondName of(String value) {
        notNull(value, "Second name can not be null");
        notBlank(value, "Second name can not be empty or blank");
        validState(value.length() <= 50, "Second name can not contain more than 50 letters");
        matchesPattern(value, "[A-z]+", "Second name can contain only latin letters");

        return new SecondName(value);
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

        SecondName that = (SecondName) other;

        return new EqualsBuilder()
            .append(name, that.name)
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
        return "SecondName{name='" + name + "\'}";
    }
}
