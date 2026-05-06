package com.biblioteca.bibliotecaCreate.Entity.cashier;

import com.biblioteca.bibliotecaCreate.Entity.adress.Address;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataRegisterCashier;
import com.biblioteca.bibliotecaCreate.dto.cashierDTO.DataUpdateCashier;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cashiers")
@Entity(name = "cashier")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cashier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String mobileNumber;

    private boolean active;

    @Embedded
    private Address address;

    public Cashier(DataRegisterCashier data){
        this.name = data.name();
        this.email = data.email();
        this.mobileNumber = data.mobileNumber();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updateInfo(@Valid DataUpdateCashier dataUpdateCashier) {
        if (dataUpdateCashier.name() != null){
            this.name = dataUpdateCashier.name();
        }
        if (dataUpdateCashier.email() != null){
            this.email = dataUpdateCashier.email();
        }
        if (dataUpdateCashier.mobileNumber() != null){
            this.mobileNumber = dataUpdateCashier.mobileNumber();
        }
        if (dataUpdateCashier.address() != null){
            this.address.updateInfo(dataUpdateCashier.address());
        }
    }
    public void delete(){
        this.active = false;
    }
}
