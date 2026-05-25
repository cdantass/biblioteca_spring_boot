package com.biblioteca.bibliotecaCreate.Service.clientService;

import com.biblioteca.bibliotecaCreate.Entity.client.Client;
import com.biblioteca.bibliotecaCreate.Repository.ClientRepository;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataDetailClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataListClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataRegisterClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataUpdateClient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public DataDetailClient register(DataRegisterClient dataRegisterClient){
        var client = clientRepository.save(new Client(dataRegisterClient));
        return new DataDetailClient(client);
    }

    public Page<DataListClient> list(Pageable pageable){
        return clientRepository.findAll(pageable)
                .map(DataListClient::new);
    }

    public DataDetailClient detail(Long id){
        var client = clientRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Client not found"));
        return new DataDetailClient(client);
    }

    @Transactional
    public DataUpdateClient update(Long id, DataUpdateClient dataUpdateClient){
        var client = clientRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Client not found"));
        return new DataUpdateClient(client);
    }

    public void delete(Long id){
        var client = clientRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Client not found"));
        client.delete();
    }
}