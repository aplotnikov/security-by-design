package io.github.aplotnikov.v1.domain;

interface ClientRepository {

    void save(Client client);

    Client findById(long id);

}
