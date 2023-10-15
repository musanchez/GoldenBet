package com.goldenBet.GoldenBet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario {

    @Id
    private String id;

    @Column(
            length = 200,
            nullable = false
    )
    private String nombre;

    @Column(
            length = 16,
            nullable = false,
            unique = true
    )
    private String cedula;

    @Column(
            length = 50,
            nullable = false
    )
    private String sexo;

    @Column(
            length = 200,
            nullable = false,
            unique = true
    )
    private String correo;

    @Column(
            length = 20,
            nullable = false,
            unique = true
    )
    private String telefono;

    @Column(
            length = 30,
            nullable = false,
            unique = true
    )
    private String username;

    @Column(
            //default max length
            nullable = false
    )
    private String password;

    @Column(
            name = "pregunta_seguridad"
            //default max length
    )
    private String pregunta;

    @Column(
            name = "respuesta_seguridad"
            //default max length
    )
    private String respuesta;

    @OneToMany(
            mappedBy = "usuario",
            fetch = FetchType.LAZY
    )
    private List<Apuesta> apuestaList;
}
