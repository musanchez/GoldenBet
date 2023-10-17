package com.goldenBet.GoldenBet.repository;

import com.goldenBet.GoldenBet.models.Deporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RepoDeporte")
public interface IRepositoryDeporte extends JpaRepository<Deporte, String> {
}
