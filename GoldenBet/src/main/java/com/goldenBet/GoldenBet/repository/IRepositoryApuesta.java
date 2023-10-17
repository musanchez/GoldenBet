package com.goldenBet.GoldenBet.repository;

import com.goldenBet.GoldenBet.models.Apuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RepoApuesta")
public interface IRepositoryApuesta extends JpaRepository<Apuesta, String> {
}
