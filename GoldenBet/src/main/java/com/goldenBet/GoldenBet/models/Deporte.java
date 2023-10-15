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
public class Deporte {
    @Id
    private String id;

    @Column(
            length = 80,
            nullable = false,
            unique = true
    )
    private String nombre;

    @OneToMany(
            mappedBy = "deporte",
            fetch = FetchType.LAZY
    )
    private List<Competencia> competenciaList;

    @OneToMany(
            mappedBy = "deporte",
            fetch = FetchType.LAZY
    )
    private List<Evento> eventosList;
}
