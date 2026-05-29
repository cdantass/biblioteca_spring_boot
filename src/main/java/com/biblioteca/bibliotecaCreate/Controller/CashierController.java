package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Entity.cashier.Cashier;
import com.biblioteca.bibliotecaCreate.Repository.CashierRepository;
import com.biblioteca.bibliotecaCreate.Service.cashierService.CashierService;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataDetailCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataListCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataRegisterCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataUpdateCashier;
import com.biblioteca.bibliotecaCreate.infra.exception.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cashiers")
public class CashierController {

    @Autowired
    private CashierService cashierService;


    @PostMapping
    public ResponseEntity<DataDetailCashier> register(
            @RequestBody @Valid DataRegisterCashier data,
            UriComponentsBuilder uriBuilder) {

        var dataDetail = cashierService.register(data);
        var uri = uriBuilder
                .path("/cashiers/{id}")
                .buildAndExpand(dataDetail.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(dataDetail);
    }

    @GetMapping
    public ResponseEntity<Page<DataListCashier>> list(Pageable pageable) {
        return ResponseEntity.ok(cashierService
                .list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailCashier> detail(@PathVariable Long id) {
        return ResponseEntity.ok(cashierService
                .detail(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataUpdateCashier> update(
            @PathVariable Long id,
            @RequestBody @Valid DataUpdateCashier data) {
        return ResponseEntity.ok(cashierService
                .update(id, data));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cashierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}