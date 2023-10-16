package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.UsuarioDto;
import com.goldenBet.GoldenBet.models.Usuario;
import com.goldenBet.GoldenBet.repository.IRepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUsuario implements IServiceUsuario {

    @Autowired
    private IRepositoryUsuario repositoryUsuario;
    @Override
    public void create(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();

        System.out.println(repositoryUsuario.count());

        usuario.setId("us " + (repositoryUsuario.count() + 1));
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setCedula(usuarioDto.getCedula());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setPassword(usuarioDto.getClave());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setPregunta(usuarioDto.getPregunta());
        usuario.setUsername(usuarioDto.getUsuario());
        usuario.setRespuesta(usuarioDto.getRespuesta());
        usuario.setSexo(usuarioDto.getSexo());

        repositoryUsuario.save(usuario);
    }

    @Override
    public List<Usuario> getAll() {
        return repositoryUsuario.findAll();
    }
}
