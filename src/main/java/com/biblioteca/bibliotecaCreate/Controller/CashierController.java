package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Entity.cashier.Cashier;
import com.biblioteca.bibliotecaCreate.Repository.CashierRepository;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataDetailCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataListCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataRegisterCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataUpdateCashier;
import com.biblioteca.bibliotecaCreate.infra.exception.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cashiers")
public class CashierController {

    private final CashierRepository repository;

    public CashierController(CashierRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailCashier> register(
            @RequestBody @Valid DataRegisterCashier data,
            UriComponentsBuilder uriBuilder) {

        var cashier = new Cashier(data);
        cashier = repository.save(cashier);

        var uri = uriBuilder
                .path("/cashiers/{id}")
                .buildAndExpand(cashier.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DataDetailCashier(cashier));
    }

    @GetMapping
    public ResponseEntity<Page<DataListCashier>> list(Pageable pageable) {

        var page = repository
                .findAllByActiveTrue(pageable)
                .map(DataListCashier::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailCashier> detail(@PathVariable Long id) {

        var cashier = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cashier not found"));

        return ResponseEntity.ok(new DataDetailCashier(cashier));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataDetailCashier> update(
            @PathVariable Long id,
            @RequestBody @Valid DataUpdateCashier data) {

        var cashier = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cashier not found"));

        cashier.updateInfo(data);

        return ResponseEntity.ok(new DataDetailCashier(cashier));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        var cashier = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cashier not found"));

        cashier.delete();

        return ResponseEntity.noContent().build();
    }
}