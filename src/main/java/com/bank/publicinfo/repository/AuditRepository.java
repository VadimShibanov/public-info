package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий, который используется для фиксации создания и изменения данных по запросу банковского аудита.
 */

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

    List<Audit> findByEntityType(String entityType);
    boolean existsByEntityType(String entityType);
}
