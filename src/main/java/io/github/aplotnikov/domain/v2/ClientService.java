package io.github.aplotnikov.domain.v2;

import static com.google.common.base.Preconditions.checkNotNull;
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
        checkNotNull(client, "Client can not be null");

        LOG.info("Registering client with id: {}", client.getId());

        repository.save(client);
    }
}
