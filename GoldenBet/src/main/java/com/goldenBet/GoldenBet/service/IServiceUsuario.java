package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.UsuarioDto;
import com.goldenBet.GoldenBet.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceUsuario {
    public void create(UsuarioDto usuarioDto);

    public List<Usuario> getAll();

    public void delete(String id);




}
