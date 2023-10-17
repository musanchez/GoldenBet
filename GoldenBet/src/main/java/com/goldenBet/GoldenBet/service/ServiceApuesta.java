package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.ApuestaDTO;
import com.goldenBet.GoldenBet.models.Admin;
import com.goldenBet.GoldenBet.models.Apuesta;
import com.goldenBet.GoldenBet.models.Evento;
import com.goldenBet.GoldenBet.models.Usuario;
import com.goldenBet.GoldenBet.repository.IRepositoryAdmin;
import com.goldenBet.GoldenBet.repository.IRepositoryApuesta;
import com.goldenBet.GoldenBet.repository.IRepositoryEvento;
import com.goldenBet.GoldenBet.repository.IRepositoryUsuario;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("ServiceApuesta")
public class ServiceApuesta implements IServiceApuesta {

    private final IRepositoryEvento repoEvento;

    private final IRepositoryAdmin repoAdmin;

    private final IRepositoryUsuario repoUsuario;

    private final IRepositoryApuesta repoApuesta;

    @Autowired
    public ServiceApuesta(
            @Qualifier("RepoEvento") IRepositoryEvento eventoRepo,
            @Qualifier("RepoAdmin") IRepositoryAdmin adminRepo,
            @Qualifier("RepoUsuario") IRepositoryUsuario usuarioRepo,
            @Qualifier("RepoApuesta") IRepositoryApuesta apuestaRepo
    ) {
        repoEvento = eventoRepo;
        repoAdmin = adminRepo;
        repoUsuario = usuarioRepo;
        repoApuesta = apuestaRepo;
    }

    @Override
    @SneakyThrows
    public void create(ApuestaDTO apuestaDTO) {
        String idAdmin, idUsuario, idEvento;
        idAdmin = apuestaDTO.getIdAdmin();
        idUsuario = apuestaDTO.getIdUsuario();
        idEvento = apuestaDTO.getIdEvento();

        boolean admin_existe = repoAdmin.existsById(idAdmin);
        boolean usuario_existe = repoUsuario.existsById(idUsuario);
        boolean evento_existe = repoEvento.existsById(idEvento);

        if(!admin_existe)
            throw new Exception("El admin a ser encargado no existe");

        if(!usuario_existe)
            throw new Exception("El apostador no existe");

        if(!evento_existe)
            throw new Exception("El evento para la apuesta no existe");

        //atributos del POJO a asignar mediante DTO
        Apuesta apuesta = new Apuesta();
        String idApuesta = "apt-" + (repoApuesta.count() + 1);
        String descripcionApuesta = apuestaDTO.getDescripcion();
        BigDecimal montoApuesta = apuestaDTO.getMonto();
        Admin adminEncargado = repoAdmin.findById(idAdmin).get();
        Usuario userApostador = repoUsuario.findById(idUsuario).get();
        Evento eventoApuesta = repoEvento.findById(idEvento).get();

        //attachment
        apuesta.setId(idApuesta);
        apuesta.setDescripcion(descripcionApuesta);
        apuesta.setMonto(montoApuesta);
        apuesta.setAdminRep(adminEncargado);
        apuesta.setUsuario(userApostador);
        apuesta.setEvento(eventoApuesta);

        repoApuesta.save(apuesta);
    }

    @Override
    public List<Apuesta> getAll() {
        return repoApuesta.findAll();
    }
}
