package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.repository.LicenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository licenseRepository;

    /**
     * Метод добавляет лицензию банка
     */
    @Override
    public void add(License license) {
        licenseRepository.save(license);
    }

    /**
     * Метод удаляет лицензию по bankDetailsId
     */
    @Override
    public void delete(Long bankDetailsId) {
        List<License> licenseList = licenseRepository.findLicenseByBankDetailsId(bankDetailsId);

        for (License acc: licenseList) {
            licenseRepository.delete(acc);
        }
    }

    /**
     * Метод возвращает лицензию банка по данным банка
     */
    @Override
    public List<License> getLicenseByBankDetailsId(Long bankDetailsId) {
        return licenseRepository.findLicenseByBankDetailsId(bankDetailsId);
    }

    /**
     * Метод проверяет существование лицензии по bankDetailsId
     */
    @Override
    public boolean checkIfExistsByBankDetailsId(Long bankDetailsId) {
        return licenseRepository.existsByBankDetails(bankDetailsId);
    }
}
