package io.github.aplotnikov.domain.v2

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class PersonalIdSpec extends Specification {

    void 'should be impossible to create personal id when value is null'() {
        when:
            PersonalId.of(null)
        then:
            IllegalArgumentException exception = thrown()
            exception.message == 'Personal ID can not be null'
    }

    void 'should be impossible to create personal id when value is blank or empty'() {
        when:
            PersonalId.of(value)
        then:
            IllegalArgumentException exception = thrown()
            exception.message == 'Personal ID can not be empty or blank'
        where:
            value << [
                '',
                ' ',
                ' '.repeat(5)
            ]
    }

    void 'should be impossible to create personal id when value is longer than 50 symbols'() {
        when:
            PersonalId.of('1'.repeat(lenght))
        then:
            IllegalArgumentException exception = thrown()
            exception.message == 'Personal ID can not contain more or less than 11 symbols'
        where:
            lenght << [
                10,
                12
            ]
    }

    void 'should be impossible to create personal id when value contains another symbols except latin letters'() {
        when:
            PersonalId.of(value)
        then:
            IllegalArgumentException exception = thrown()
            exception.message == 'Personal ID can not contain anything except digits'
        where:
            value << [
                '1'.repeat(10) + 'A',
                '1'.repeat(10) + '!',
                'A' + '1'.repeat(10),
                '1'.repeat(5) + 'A' + '1'.repeat(5)
            ]
    }

    void 'should be impossible to create personal id when value does not pass check sum verification'() {
        when:
            PersonalId.of('44051401358')
        then:
            IllegalArgumentException exception = thrown()
            exception.message == 'Personal ID does not pass check sum validation'
    }

    void 'should be possible to create personal id'() {
        when:
            PersonalId personalId = PersonalId.of('95051480088')
        then:
            personalId.value == '95051480088'
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(PersonalId)
                .usingGetClass()
                .verify()
    }

}
