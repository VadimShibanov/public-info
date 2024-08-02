package com.bank.publicinfo.service;


import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.repository.BankDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankDetailsServiceImpl implements BankDetailsService {

    private final BankDetailsRepository bankDetailsRepository;

    /**
     * Метод добавляет данные банка
     */
    @Override
    public void add(BankDetails bankDetails) {
        bankDetailsRepository.save(bankDetails);
    }

    /**
     * Метод удаляет банкомат по номеру бик
     */
    @Override
    public void delete(Long bik) {
        List<BankDetails> bankDetailsList = bankDetailsRepository.findBankDetailsByBik(bik);

        for (BankDetails acc: bankDetailsList) {
            bankDetailsRepository.delete(acc);
        }
    }

    /**
     * Метод возвращает данные банка, который найден по бик
     */
    @Override
    public List<BankDetails> getBankDetailsByBik(Long bik) {
        return bankDetailsRepository.findBankDetailsByBik(bik);
    }

    /**
     * Метод проверяет существования банкомата по бик
     */
    @Override
    public boolean checkIfExistsByBik(Long bik) {
        return bankDetailsRepository.existsByBik(bik);
    }

}
