package io.github.aplotnikov.v1.domain

import spock.lang.Specification
import spock.lang.Subject

class ClientServiceSpec extends Specification {

    ClientRepository repository = Mock()

    @Subject
    ClientService service = new ClientService(repository)

    PrintStream originalSystemOut

    ByteArrayOutputStream systemOutContent = new ByteArrayOutputStream()

    void setup() {
        originalSystemOut = System.out
        System.setOut(new PrintStream(systemOutContent))
    }

    void cleanup() {
        System.setOut(originalSystemOut)
    }

    void 'should log client sensitive information'() {
        given:
            Client client = new Client(
                id: 1,
                firstName: 'Andrii',
                secondName: 'Plotnikov',
                password: 'my password',
                personalId: '95051480088'
            )
        when:
            service.register(client)
        then:
            1 * repository.save(client)
        and:
            with(systemOutContent.toString()) {
                originalSystemOut.println(it)

                contains('password')
                contains(client.password)

                contains('personalId')
                contains(client.personalId)
            }
    }
}
