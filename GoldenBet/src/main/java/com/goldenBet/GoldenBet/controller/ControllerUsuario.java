package com.goldenBet.GoldenBet.controller;


import com.goldenBet.GoldenBet.dto.UsuarioDto;
import com.goldenBet.GoldenBet.models.Usuario;
import com.goldenBet.GoldenBet.service.IServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class ControllerUsuario {
    @Autowired
    private IServiceUsuario serviceUsuario;

    @PostMapping("/save")
    public void save(@RequestBody UsuarioDto usuarioDto) {
        serviceUsuario.create(usuarioDto);
    }

    @GetMapping("/get")
    public List<Usuario> getAll() {
        return serviceUsuario.getAll();
    }
}
