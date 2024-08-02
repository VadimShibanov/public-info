package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.repository.AtmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AtmServiceImpl implements AtmService {

    private final AtmRepository atmRepository;

    /**
     * Метод добавляет банкомат
     */
    @Override
    public void add(Atm atm) {
        atmRepository.save(atm);
    }

    /**
     * Метод удаляет банкоматы по адресу
     */
    @Override
    public void delete(String address) {
        List<Atm> atmList = atmRepository.findAtmByAddress(address);

        for (Atm acc: atmList) {
            atmRepository.delete(acc);
        }
    }

    /**
     * Метод возвращает все банкоматы, которые находятся по адресу
     */
    @Override
    public List<Atm> getAtmByAddress(String address) {
        return atmRepository.findAtmByAddress(address);
    }

    /**
     * Метод проверяет существование банкомата
     */
    @Override
    public boolean checkIfExistsByAddress(String address) {
        return atmRepository.existsByAddress(address);
    }
}