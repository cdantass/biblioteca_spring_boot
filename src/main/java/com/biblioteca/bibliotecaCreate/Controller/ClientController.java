package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Entity.client.Client;
import com.biblioteca.bibliotecaCreate.Repository.ClientRepository;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataUpdateCashier;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataDetailClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataListClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataRegisterClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataUpdateClient;
import com.biblioteca.bibliotecaCreate.infra.exception.NotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository repository;

    public ClientController(ClientRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailClient> register(@RequestBody @Valid DataRegisterClient data, UriComponentsBuilder uriComponentsBuilder){

        var client = new Client(data);
        client = repository.save(client);

        var uri = uriComponentsBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailClient(client));

    }

    @GetMapping
    public ResponseEntity<Page<DataListClient>> list(Pageable pageable){
        var page = repository.findAllByActiveTrue(pageable).map(DataListClient::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailClient> detail(@PathVariable Long id){
        var client = repository.findById(id).orElseThrow(()-> new NotFoundException("Client not found"));

        return ResponseEntity.ok(new DataDetailClient(client));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataDetailClient> update(@PathVariable Long id, @RequestBody @Valid DataUpdateClient data){
        var client = repository.findById(id).orElseThrow(()-> new NotFoundException("Client not found"));
        client.updateInfo(data);

        return ResponseEntity.ok(new DataDetailClient(client));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        var client = repository.findById(id).orElseThrow(()-> new NotFoundException("Client not found"));

        client.delete();

        return ResponseEntity.noContent().build();
    }
}
