package com.biblioteca.bibliotecaCreate.Service.cashierService;

import com.biblioteca.bibliotecaCreate.Entity.cashier.Cashier;
import com.biblioteca.bibliotecaCreate.Repository.CashierRepository;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataDetailCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataListCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataRegisterCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataUpdateCashier;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashierService {

    @Autowired
    CashierRepository cashierRepository;

    @Transactional
    public DataDetailCashier register(DataRegisterCashier dataRegisterCashier){
        var cashier = cashierRepository.save(new Cashier(dataRegisterCashier));
        return new DataDetailCashier(cashier);
    }

    public Page<DataListCashier> list(Pageable pageable){
        return cashierRepository.findAll(pageable)
                .map(DataListCashier::new);
    }

    public DataDetailCashier detail(Long id){
        var cashier = cashierRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cashier not found"));
        return new DataDetailCashier(cashier);
    }

    @Transactional
    public DataUpdateCashier update(Long id, DataUpdateCashier dataUpdateCashier){
        var cashier = cashierRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cashier not found"));

        cashier.updateInfo(dataUpdateCashier);
        return new DataUpdateCashier(cashier);
    }

    @Transactional
    public void delete(Long id){
        var cashier = cashierRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cashier not found"));
        cashier.delete();
    }
}
