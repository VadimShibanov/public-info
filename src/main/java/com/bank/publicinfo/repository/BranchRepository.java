package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий отделения
 */

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findBranchesByAddress(String address);
    boolean existsByAddress(String address);
}
