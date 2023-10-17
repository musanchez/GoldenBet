package com.goldenBet.GoldenBet.repository;

import com.goldenBet.GoldenBet.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RepoAdmin")
public interface IRepositoryAdmin extends JpaRepository<Admin, String> {
}
