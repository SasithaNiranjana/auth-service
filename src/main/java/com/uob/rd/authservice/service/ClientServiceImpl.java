package com.uob.rd.authservice.service;

import com.uob.rd.authservice.entity.Client;
import com.uob.rd.authservice.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    @Override
    public void saveClient(Client client) {
        this.clientRepository.saveAndFlush(client);
    }

    @Override
    public Optional<Client> findClientByClientId(String clientId) {
        return this.clientRepository.findByClientId(clientId);
    }
}
