package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AtmService {
    void add(Atm atm);

    void delete(String address);

    List<Atm> getAtmByAddress(String address);

    boolean checkIfExistsByAddress(String address);
}
