package com.goldenBet.GoldenBet.repository;

import com.goldenBet.GoldenBet.models.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RepoCompetencia")
public interface IRepositoryCompetencia extends JpaRepository<Competencia, String> {
}
