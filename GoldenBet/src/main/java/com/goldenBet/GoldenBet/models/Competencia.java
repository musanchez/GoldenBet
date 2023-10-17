package com.goldenBet.GoldenBet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Deporte deporte;

    @JsonIgnore
    @OneToMany(
            mappedBy = "competencia",
            fetch = FetchType.LAZY
    )
    private List<Evento> eventosList;
}
