package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.AdminDTO;
import com.goldenBet.GoldenBet.models.Admin;
import com.goldenBet.GoldenBet.repository.IRepositoryAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ServiceAdmin")
public class ServiceAdmin implements IServiceAdmin{

    private final IRepositoryAdmin repoAdmin;

    @Autowired
    public ServiceAdmin(
            @Qualifier("RepoAdmin") IRepositoryAdmin adminRepo
    ) {
        repoAdmin = adminRepo;
    }

    @Override
    public void create(AdminDTO adminDTO) {
        String idAdmin = "adm-" + (repoAdmin.count() + 1);
        String nombreAdmin = adminDTO.getNombre();
        String cedulaAdmin = adminDTO.getCedula();

        //entidad a persistir con atributos del DTO
        Admin admin = new Admin();
        admin.setId(idAdmin);
        admin.setNombre(nombreAdmin);
        admin.setCedula(cedulaAdmin);

        repoAdmin.save(admin);
    }

    @Override
    public List<Admin> getAll() {
        return repoAdmin.findAll();
    }
}
