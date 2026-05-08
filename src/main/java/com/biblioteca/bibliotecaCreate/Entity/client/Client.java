package com.biblioteca.bibliotecaCreate.Entity.client;

import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataRegisterClient;
import com.biblioteca.bibliotecaCreate.dto.clientDTO.DataUpdateClient;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "clients")
@Entity(name = "client")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String mobileNumber;

    private boolean active;

    public Client(DataRegisterClient data){
        this.name = data.name();
        this.email = data.email();
        this.mobileNumber = data.mobilephone();
        this.active = true;

    }

    public void updateInfo(@Valid DataUpdateClient updateClient){
        if (updateClient.name() != null){
            this.name = updateClient.name();
        }
        if (updateClient.email() != null){
            this.email = updateClient.email();
        }
        if (updateClient.mobileNumber() != null){
            this.mobileNumber = updateClient.mobileNumber();
        }
    }
    public void delete(){
        this.active = false;
    }
}
