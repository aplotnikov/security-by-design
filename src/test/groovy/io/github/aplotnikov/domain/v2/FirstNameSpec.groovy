package io.github.aplotnikov.domain.v2

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class FirstNameSpec extends Specification {

    void 'should be impossible to create first name when value is null'() {
        when:
            FirstName.of(null)
        then:
            NullPointerException exception = thrown()
            exception.message == 'First name can not be null'
    }

    void 'should be impossible to create first name when value is blank or empty'() {
        when:
            FirstName.of(value)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'First name can not be empty or blank'
        where:
            value << [
                '',
                ' ',
                ' '.repeat(5)
            ]
    }

    void 'should be impossible to create first name when value is longer than 30 symbols'() {
        when:
            FirstName.of('A'.repeat(31))
        then:
            IllegalStateException exception = thrown()
            exception.message == 'First name can not contain more than 30 letters'
    }

    void 'should be impossible to create first name when value contains another symbols except latin letters'() {
        when:
            FirstName.of(value)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'First name can contain only latin letters'
        where:
            value << [
                'A'.repeat(29) + '1',
                'A'.repeat(29) + '!',
                '1' + 'A'.repeat(29),
                'A'.repeat(14) + '1' + 'A'.repeat(15),
                '\'\' or 1=1'
            ]
    }

    void 'should be possible to create first name'() {
        when:
            FirstName name = FirstName.of('Andrii')
        then:
            name.value == 'Andrii'
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(FirstName)
                .usingGetClass()
                .verify()
    }

}
