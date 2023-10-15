package com.goldenBet.GoldenBet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Apuesta {
    @Id
    private String id;

    @Column(
            //default max length
            nullable = false
    )
    private String descripcion;

    @Column(
        nullable = false,
        precision = 8,
        scale = 2
    )
    private BigDecimal monto;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "AdminEncargado",
            foreignKey = @ForeignKey(name = "FK_AdminEncargado")
    )
    private Admin adminRep;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "UsuarioApostador",
            foreignKey = @ForeignKey(name = "FK_UsuarioApostador")
    )
    private Usuario usuario;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "EventoApuesta",
            foreignKey = @ForeignKey(name = "FK_EventoApuesta")
    )
    private Evento evento;
}
