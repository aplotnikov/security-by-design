package io.github.aplotnikov.domain.v2

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class SecondNameSpec extends Specification {

    void 'should be impossible to create second name when value is null'() {
        when:
            SecondName.of(null)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Second name can not be null'
    }

    void 'should be impossible to create second name when value is blank or empty'() {
        when:
            SecondName.of(value)
        then:
            IllegalArgumentException exception = thrown()
            exception.message == 'Second name can not be empty or blank'
        where:
            value << [
                '',
                ' ',
                ' '.repeat(5)
            ]
    }

    void 'should be impossible to create second name when value is longer than 50 symbols'() {
        when:
            SecondName.of('A'.repeat(51))
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Second name can not contain more than 50 letters'
    }

    void 'should be impossible to create second name when value contains another symbols except latin letters'() {
        when:
            SecondName.of(value)
        then:
            IllegalArgumentException exception = thrown()
            exception.message == 'Second name can contain only latin letters'
        where:
            value << [
                'A'.repeat(29) + '1',
                'A'.repeat(29) + '!',
                '1' + 'A'.repeat(29),
                'A'.repeat(14) + '1' + 'A'.repeat(15),
                '\'\' or 1=1'
            ]
    }

    void 'should be possible to create second name'() {
        when:
            SecondName name = SecondName.of('Plotnikov')
        then:
            name.value == 'Plotnikov'
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(SecondName)
                .usingGetClass()
                .verify()
    }

}
