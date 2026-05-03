package com.biblioteca.bibliotecaCreate.Controller;

import com.biblioteca.bibliotecaCreate.Entity.cashier.Cashier;
import com.biblioteca.bibliotecaCreate.Repository.CashierRepository;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataDetailCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataListCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataRegisterCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataUpdateCashier;
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
    private CashierRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody DataRegisterCashier data, UriComponentsBuilder uriComponentsBuilder){
        var cashier = new Cashier(data);
        repository.save(new Cashier(data));

        var uri =uriComponentsBuilder.path("/cashiers/{id}").buildAndExpand(cashier.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailCashier(cashier));
    }

    @GetMapping
    public ResponseEntity<Page<DataListCashier>> list(Pageable pageable){
        var page = repository.findAllByActiveTrue(pageable).map(DataListCashier::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateCashier dataUpdateCashier){
        var cashier = repository.getReferenceById(dataUpdateCashier.id());
        cashier.updateinfo(dataUpdateCashier);

        return ResponseEntity.ok(new DataDetailCashier(cashier));
    }
}
