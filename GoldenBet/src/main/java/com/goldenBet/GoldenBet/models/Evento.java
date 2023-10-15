package com.goldenBet.GoldenBet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Evento {

    @Id
    private String id;

    @Column(
            name = "primer_participante",
            nullable = false,
            length = 100
    )
    private String participante1;

    @Column(
            name = "segundo_participante",
            nullable = false,
            length = 100
    )
    private String participante2;

    @Column(
            nullable = false
    )
    private LocalDate fecha;

    @Column(
            nullable = false
    )
    private LocalTime hora;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "DeporteId",
            foreignKey = @ForeignKey(name = "FK_DeporteEvento")
    )
    private Deporte deporte;

    @OneToMany(
            mappedBy = "evento",
            fetch = FetchType.LAZY
    )
    private List<Apuesta> apuestaList;
}
