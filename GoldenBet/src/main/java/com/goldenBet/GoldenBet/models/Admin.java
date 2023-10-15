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
public class Admin {
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

    @OneToMany(
            mappedBy = "adminRep",
            fetch = FetchType.LAZY
    )
    private List<Apuesta> apuestaList;
}
