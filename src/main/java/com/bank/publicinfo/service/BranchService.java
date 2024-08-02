package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Branch;

import java.util.List;

public interface BranchService {

    void add(Branch branch);

    void delete(String address);

    List<Branch> getBranchesByAddress(String address);

    boolean checkIfExistsByAddress(String address);
}
