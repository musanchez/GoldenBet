package com.goldenBet.GoldenBet.repository;

import com.goldenBet.GoldenBet.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryUsuario extends JpaRepository<Usuario, String> {


}
