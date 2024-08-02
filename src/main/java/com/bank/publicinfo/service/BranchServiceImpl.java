package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.repository.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    /**
     * Метод добавляет отделение
     */
    @Override
    public void add(Branch branch) {
        branchRepository.save(branch);
    }

    /**
     * Метод удаляет список отделений по адресу
     */
    @Override
    public void delete(String address) {
        List<Branch> branchList = branchRepository.findBranchesByAddress(address);

        for (Branch acc: branchList) {
            branchRepository.delete(acc);
        }
    }

    /**
     * Метод возвращается все отделения, находящиеся по адресу
     */
    @Override
    public List<Branch> getBranchesByAddress(String address) {
        return branchRepository.findBranchesByAddress(address);
    }

    /**
     * Метод проверяет существование отделения по адресу
     */
    @Override
    public boolean checkIfExistsByAddress(String address) {
        return branchRepository.existsByAddress(address);
    }

}
