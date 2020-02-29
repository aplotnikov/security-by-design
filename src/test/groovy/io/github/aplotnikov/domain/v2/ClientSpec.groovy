package io.github.aplotnikov.domain.v2

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Shared
import spock.lang.Specification

class ClientSpec extends Specification {

    @Shared
    ClientId clientId = ClientId.of(1)

    @Shared
    FirstName firstName = FirstName.of('Andrii')

    @Shared
    SecondName secondName = SecondName.of('Plotnikov')

    @Shared
    Password password = Password.of('myPassword'.chars)

    @Shared
    PersonalId personalId = PersonalId.of('95051480088')

    void 'should be impossible to create a client when client id is null'() {
        when:
            new Client(null, firstName, secondName, password, personalId)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Client id can not be null'

    }

    void 'should be impossible to create a client when first name is null'() {
        when:
            new Client(clientId, null, secondName, password, personalId)
        then:
            NullPointerException exception = thrown()
            exception.message == 'First name can not be null'
    }

    void 'should be impossible to create a client when second name is null'() {
        when:
            new Client(clientId, firstName, null, password, personalId)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Second name can not be null'
    }

    void 'should be impossible to create a client when password is null'() {
        when:
            new Client(clientId, firstName, secondName, null, personalId)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Password can not be null'
    }

    void 'should be impossible to create a client when personal id is null'() {
        when:
            new Client(clientId, firstName, secondName, password, null)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Personal id can not be null'
    }

    void 'should be possible to create a client instance'() {
        when:
            Client client = new Client(clientId, firstName, secondName, password, personalId)
        then:
            with(client) {
                id == clientId
                firstName == this.firstName
                secondName == this.secondName
                password == this.password
                personalId == this.personalId
            }
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(Client)
                .withOnlyTheseFields('id', 'personalId')
                .usingGetClass()
                .verify()
    }
}
