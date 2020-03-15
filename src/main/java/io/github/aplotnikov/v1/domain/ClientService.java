package io.github.aplotnikov.v1.domain;

import static java.lang.invoke.MethodHandles.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class ClientService {

    private static final Logger LOG = LoggerFactory.getLogger(lookup().lookupClass());

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void register(Client client) {
        LOG.info("Registering client: {}", client);
        repository.save(client);
    }
}
