package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.DeporteDTO;
import com.goldenBet.GoldenBet.models.Deporte;
import com.goldenBet.GoldenBet.repository.IRepositoryDeporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ServiceDeporte")
public class ServiceDeporte implements IServiceDeporte{

    private final IRepositoryDeporte repoDeporte;

    @Autowired
    public ServiceDeporte(
            @Qualifier("RepoDeporte") IRepositoryDeporte deporteRepo
    ) {
        repoDeporte = deporteRepo;
    }

    @Override
    public void create(DeporteDTO deporteDTO) {
        String idDeporte = "dep-" + (repoDeporte.count() + 1);
        String nombreDeporte = deporteDTO.getNombre();

        //entidad a persistir con atributos del DTO
        Deporte deporte = new Deporte();
        deporte.setId(idDeporte);
        deporte.setNombre(nombreDeporte);

        repoDeporte.save(deporte);
    }

    @Override
    public List<Deporte> getAll() {
        return repoDeporte.findAll();
    }
}
