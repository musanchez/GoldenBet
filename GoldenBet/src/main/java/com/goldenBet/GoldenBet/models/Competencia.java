package com.goldenBet.GoldenBet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Competencia {
    @Id
    private String id;

    @Column(
            length = 80,
            nullable = false,
            unique = true
    )
    private String nombre;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "deporteId",
            foreignKey = @ForeignKey(name = "FK_Deporte_ID")
    )
    private Deporte deporte;
}
