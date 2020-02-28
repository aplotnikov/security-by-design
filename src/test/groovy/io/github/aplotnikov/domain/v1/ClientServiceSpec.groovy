package io.github.aplotnikov.domain.v1

import spock.lang.Specification
import spock.lang.Subject

class ClientServiceSpec extends Specification {

    ClientRepository repository = Mock()

    @Subject
    ClientService service = new ClientService(repository)

    void 'should log client sensitive information'() {
        given:
            Client client = new Client(
                id: 1,
                firstName: 'Andrii',
                secondName: 'Plotnikov',
                password: 'my password'
            )
        when:
            service.register(client)
        then:
            1 * repository.save(client)
    }
}
