package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Audit;

import java.util.List;

public interface AuditService {
    void add(Audit audit);

    void delete(String entityType);

    List<Audit> getAuditsByEntityType(String entityType);

    boolean checkIfExistsByEntityType(String entityType);
}
