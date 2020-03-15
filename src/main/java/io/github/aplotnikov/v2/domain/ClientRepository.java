package io.github.aplotnikov.v2.domain;

interface ClientRepository {

    void save(Client client);

    Client findById(ClientId id);

}
