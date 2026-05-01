package com.biblioteca.bibliotecaCreate.Entity.cashier;

import com.biblioteca.bibliotecaCreate.Entity.adress.Address;
import com.biblioteca.bibliotecaCreate.dto.DataRegisterCashier;
import com.biblioteca.bibliotecaCreate.dto.DataRegisterClient;
import jakarta.persistence.*;
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
        this.address = data.adress();
        this.active = true;
    }
}
