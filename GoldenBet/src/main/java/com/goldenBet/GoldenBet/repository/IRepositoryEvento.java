package com.goldenBet.GoldenBet.repository;

import com.goldenBet.GoldenBet.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("RepoEvento")
public interface IRepositoryEvento extends JpaRepository<Evento, String> {
    //metodo con @query annotation para GET por participante 1
    // %:%
    //@Param()
    @Query("select a from Evento a where a.participante1 like %:participanteName%")
    List<Evento> getByParticipante1(@Param("participanteName") String participanteName);

    //metodo con @query annotation para GET por participante2
    // %:%
    //@Param()
    @Query("select a from Evento a where a.participante2 like %:participanteName%")
    List<Evento> getByParticipante2(@Param("participanteName") String participanteName);

    //metodo con @query annotation para GET por nombre de competencia (JPQL de entidad)
    //%:%
    //@Param()
    @Query("select a from Evento a where a.competencia.nombre like %:competenciaName%")
    List<Evento> getByCompetencia(@Param("competenciaName") String competenciaName);

    @Query("select a from Evento a where a.estado = :estado")
    List<Evento> getByEstado(@Param("estado") String estado);

    @Query("select a from Evento a where a.descripcion like %:descripcion%")
    List<Evento> getByDescripcion(@Param("descripcion") String descripcion);

}
