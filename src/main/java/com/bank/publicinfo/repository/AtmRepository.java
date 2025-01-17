package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Atm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {

    List<Atm> findAtmByAddress(String address);
    boolean existsByAddress(String address);
}
