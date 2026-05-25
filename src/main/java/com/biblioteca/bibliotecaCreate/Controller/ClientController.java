package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Service.clientService.ClientService;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataDetailClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataListClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataRegisterClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataUpdateClient;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailClient> register(@RequestBody @Valid DataRegisterClient data, UriComponentsBuilder uriComponentsBuilder){
        var client = clientService.register(data);

        var uri = uriComponentsBuilder
                .path("/client/{id}")
                .buildAndExpand(client.id()).toUri();

        return ResponseEntity.created(uri).body(client);
    }

    @GetMapping
    public ResponseEntity<Page<DataListClient>> list(Pageable pageable){
        return ResponseEntity.ok(clientService.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailClient> detail(@PathVariable Long id){
        return ResponseEntity.ok(clientService.detail(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataUpdateClient> update(@PathVariable Long id, @RequestBody @Valid DataUpdateClient data){
        return ResponseEntity.ok(clientService.update(id, data));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
