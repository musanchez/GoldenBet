package com.goldenBet.GoldenBet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Admin adminRep;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "UsuarioApostador",
            foreignKey = @ForeignKey(name = "FK_UsuarioApostador")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "EventoApuesta",
            foreignKey = @ForeignKey(name = "FK_EventoApuesta")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Evento evento;
}
