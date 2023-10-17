package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.AdminDTO;
import com.goldenBet.GoldenBet.models.Admin;
import java.util.List;

public interface IServiceAdmin {
    void create(AdminDTO adminDTO);

    List<Admin> getAll();
}
