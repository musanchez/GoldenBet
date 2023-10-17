package com.goldenBet.GoldenBet.repository;

import com.goldenBet.GoldenBet.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RepoEvento")
public interface IRepositoryEvento extends JpaRepository<Evento, String> {
}
