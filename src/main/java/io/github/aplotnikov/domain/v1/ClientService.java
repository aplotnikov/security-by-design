package io.github.aplotnikov.domain.v1;

import static java.lang.invoke.MethodHandles.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ClientService {

    private static final Logger LOG = LoggerFactory.getLogger(lookup().lookupClass());

    private final ClientRepository repository;

    ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    void register(Client client) {
        LOG.info("Registering client: {}", client);
        repository.save(client);
    }
}
