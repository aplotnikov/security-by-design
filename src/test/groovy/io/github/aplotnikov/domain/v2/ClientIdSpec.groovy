package io.github.aplotnikov.domain.v2

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class ClientIdSpec extends Specification {

    void 'should be impossible to create client id from null value'() {
        when:
            ClientId.of(null)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Client id can not be null'
    }

    void 'should be impossible to create client id from text which contains more than 10 symbols'() {
        when:
            ClientId.of('1'.repeat(11))
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Client id can not contain more than 10 symbols'
    }

    void 'should be impossible to create client id from text which contain another symbols than digits'() {
        when:
            ClientId.of(value)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Client id can not contain anything except digits'
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

    void 'should be impossible to create client id when value is less than zero'() {
        when:
            ClientId.of(-1)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Client id has to be more than zero'
    }

    void 'should be impossible to create client id when value is more than 1 000 000 000'() {
        when:
            ClientId.of(1_000_000_001)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Client id has to be less than 1 000 000 000'
    }

    void 'should be possible to create client id when '() {
        when:
            ClientId id = ClientId.of(1)
        then:
            id.value == 1
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(ClientId)
                .usingGetClass()
                .verify()
    }
}
