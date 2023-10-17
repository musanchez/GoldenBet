package com.goldenBet.GoldenBet.controller;


import com.goldenBet.GoldenBet.dto.UsuarioDto;
import com.goldenBet.GoldenBet.models.Usuario;
import com.goldenBet.GoldenBet.service.IServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class ControllerUsuario {

    private final IServiceUsuario serviceUsuario;

    @Autowired
    public ControllerUsuario(
            @Qualifier("ServiceUsuario") IServiceUsuario usuarioService
    ) {
        serviceUsuario = usuarioService;
    }

    @PostMapping("/save")
    public void save(@RequestBody UsuarioDto usuarioDto) {
        serviceUsuario.create(usuarioDto);
    }

    @GetMapping("/all")
    public List<Usuario> getAll() {
        return serviceUsuario.getAll();
    }


    @PutMapping("/update")
    public void update(@RequestBody UsuarioDto usuarioDto) {
        serviceUsuario.create(usuarioDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        serviceUsuario.delete(id);
    }

}
