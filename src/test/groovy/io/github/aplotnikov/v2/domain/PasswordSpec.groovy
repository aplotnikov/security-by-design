package io.github.aplotnikov.v2.domain

import org.apache.commons.lang3.SerializationUtils

import spock.lang.Specification

class PasswordSpec extends Specification {

    void 'should be impossible to create password when value is null'() {
        when:
            Password.of(null)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Password can not be null'
    }

    void 'should be impossible to create password when value is lower than 8 symbols'() {
        when:
            Password.of(value.chars)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Password can not contain less than 8 symbols'
        where:
            value << [
                '',
                ' ',
                ' '.repeat(7),
                'A'.repeat(7)
            ]
    }

    void 'should be impossible to create password when value is longer than 50 symbols'() {
        when:
            Password.of('A'.repeat(51).chars)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Password can not contain more than 50 symbols'
    }

    void 'should be impossible to create password when value contains another symbols except latin letters'() {
        when:
            Password.of(value.chars)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Password has to contain only digits and letters'
        where:
            value << [
                'A'.repeat(29) + '!',
                'A'.repeat(29) + ' ',
                ' ' + 'A'.repeat(29),
                'A'.repeat(14) + ' ' + 'A'.repeat(15),
                '\'\' or 1=1'
            ]
    }

    void 'should be possible to create password'() {
        when:
            Password password = Password.of('Andrii1234'.chars)
        then:
            password.value == 'Andrii1234'.chars
    }

    void 'should be impossible to extract password more than one time'() {
        given:
            Password password = Password.of('Andrii1234'.chars)
        and:
            password.value
        when:
            password.value
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Password value has already been consumed'
    }

    void 'should be impossible to see password value through toString method'() {
        given:
            Password password = Password.of('Andrii1234'.chars)
        expect:
            password.toString() == 'Password{value=\'********\'}'
    }

    void 'should be impossible to serialize password object'() {
        given:
            Password password = Password.of('Andrii1234'.chars)
        when:
            SerializationUtils.serialize(password)
        then:
            UnsupportedOperationException exception = thrown()
            exception.message == 'Not allowed operation on password object'
    }
}
