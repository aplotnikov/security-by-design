package io.github.aplotnikov.v2.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.invoke.MethodHandles.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

class ClientService {

    private static final Logger LOG = LoggerFactory.getLogger(lookup().lookupClass());

    private final ClientRepository repository;

    ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    void register(Client client) {
        checkNotNull(client, "Client can not be null");

        LOG.info("Registering client with id: {}", client.getId());

        repository.save(client);
    }
}
