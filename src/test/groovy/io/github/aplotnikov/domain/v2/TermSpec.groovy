package io.github.aplotnikov.domain.v2

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class TermSpec extends Specification {

    void 'should be impossible to create term when the value is less than one'() {
        when:
            Term.of(value as byte)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Term can not be less than one'
        where:
            value << [
                -1,
                0
            ]
    }

    void 'should be impossible to create term when the value is more than 30'() {
        when:
            Term.of(value as byte)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Term can not be more than 30'
        where:
            value << [
                31,
                100
            ]
    }

    void 'should be possible to create term'() {
        when:
            Term term = Term.of(14 as byte)
        then:
            term.value == 14
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(Term)
                .usingGetClass()
                .verify()
    }
}
