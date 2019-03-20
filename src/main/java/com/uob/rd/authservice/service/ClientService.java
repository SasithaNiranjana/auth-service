package com.uob.rd.authservice.service;

import com.uob.rd.authservice.entity.Client;

import java.util.Optional;

public interface ClientService {
    void saveClient(Client client);
    Optional<Client> findClientByClientId(String clientId);
}
