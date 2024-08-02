package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.License;

import java.util.List;

public interface LicenseService {
    void add(License license);

    void delete(Long bankDetailsId);

    List<License> getLicenseByBankDetailsId(Long bankDetailsId);

    boolean checkIfExistsByBankDetailsId(Long bankDetailsId);
}
