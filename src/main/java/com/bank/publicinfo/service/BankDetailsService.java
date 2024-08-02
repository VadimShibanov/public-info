package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;

import java.util.List;

public interface BankDetailsService {
    void add(BankDetails bankDetails);

    void delete(Long bik);

    List<BankDetails> getBankDetailsByBik(Long bik);

    boolean checkIfExistsByBik(Long bik);
}
