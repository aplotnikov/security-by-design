package io.github.aplotnikov.v2.domain;

import static java.lang.Character.isLetterOrDigit;
import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.Validate.validState;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;

final class Password implements Externalizable {

    private final char[] value;

    private boolean consumed;

    private Password(char[] value) {
        this.value = value;
    }

    static Password of(char[] value) {
        notNull(value, "Password can not be null");
        validState(value.length >= 8, "Password can not contain less than 8 symbols");
        validState(value.length <= 50, "Password can not contain more than 50 symbols");
        validState(containsValidSymbols(value), "Password has to contain only digits and letters");

        return new Password(value);
    }

    private static boolean containsValidSymbols(char[] value) {
        boolean containsDigitOrLetter = true;

        for (char character : value) {
            if (!isLetterOrDigit(character)) {
                containsDigitOrLetter = false;
            }
        }

        return containsDigitOrLetter;
    }

    char[] getValue() {
        validState(!consumed, "Password value has already been consumed");
        char[] returnValue = value.clone();
        Arrays.fill(value, '*');
        consumed = true;
        return returnValue;
    }

    @Override
    public String toString() {
        return "Password{value='********'}";
    }

    @Override
    public void writeExternal(ObjectOutput out) {
        throw new UnsupportedOperationException("Not allowed operation on password object");
    }

    @Override
    public void readExternal(ObjectInput in) {
        throw new UnsupportedOperationException("Not allowed operation on password object");
    }
}
