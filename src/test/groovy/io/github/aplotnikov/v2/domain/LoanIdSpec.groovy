package io.github.aplotnikov.v2.domain

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class LoanIdSpec extends Specification {

    void 'should be impossible to create loan id from null value'() {
        when:
            LoanId.of(null)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Loan id can not be null'
    }

    void 'should be impossible to create loan id from text which contains more than 10 symbols'() {
        when:
            LoanId.of('1'.repeat(11))
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Loan id can not contain more than 10 symbols'
    }

    void 'should be impossible to create loan id from text which contain another symbols than digits'() {
        when:
            LoanId.of(value)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Loan id can not contain anything except digits'
        where:
            value << [
                '1'.repeat(8) + 'a',
                'a' + '1'.repeat(8),
                '1'.repeat(4) + 'a' + '1'.repeat(4),
                '1'.repeat(8) + '!',
                '0 or 1=1',
                '-1'
            ]
    }

    void 'should be impossible to create loan id when value is less than zero'() {
        when:
            LoanId.of(-1)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Loan id has to be more than zero'
    }

    void 'should be impossible to create loan id when value is more than 2 000 000 000'() {
        when:
            LoanId.of(2_000_000_001)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Loan id has to be less than 2 000 000 000'
    }

    void 'should be possible to create loan id'() {
        when:
            LoanId id = LoanId.of(1)
        then:
            with(id) {
                value == 1
                !forReplace
            }
    }

    void 'should loan id to replace has status for replace'() {
        expect:
            LoanId.TO_REPLACE.forReplace
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(LoanId)
                .usingGetClass()
                .verify()
    }
}
