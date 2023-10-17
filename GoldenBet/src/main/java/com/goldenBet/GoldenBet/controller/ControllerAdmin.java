package com.goldenBet.GoldenBet.controller;

import com.goldenBet.GoldenBet.dto.AdminDTO;
import com.goldenBet.GoldenBet.models.Admin;
import com.goldenBet.GoldenBet.service.IServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ControllerAdmin {

    private final IServiceAdmin serviceAdmin;

    @Autowired
    public ControllerAdmin(
            @Qualifier("ServiceAdmin") IServiceAdmin adminService
    ) {
        serviceAdmin = adminService;
    }

    @PostMapping("/save")
    public void save(@RequestBody AdminDTO adminDTO) {
        serviceAdmin.create(adminDTO);
    }

    @GetMapping("/all")
    public List<Admin> getAll() {
        return serviceAdmin.getAll();
    }
}
