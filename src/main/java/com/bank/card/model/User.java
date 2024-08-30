package com.bank.card.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private String name;

    /**
     * Metodos propios de la object value User
     * Si, por ejemplo tuvieramos un email ,
     * podemos crear una clase Email con sus propias validaciones, metodos y declarar aqui el campo Email
     */
}
