package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.repository.AuditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    /**
     * Метод добавляет записи в таблицу аудит
     */
    @Override
    public void add(Audit audit) {
        auditRepository.save(audit);
    }

    /**
     * Метод удаляет лист аудитов по entityType
     */
    @Override
    public void delete(String entityType) {
        List<Audit> auditList = auditRepository.findByEntityType(entityType);

        for (Audit acc: auditList) {
            auditRepository.delete(acc);
        }
    }

    /**
     * Метод возвращает аудит по EntityType
     */
    @Override
    public List<Audit> getAuditsByEntityType(String entityType) {
        return auditRepository.findByEntityType(entityType);
    }

    /**
     * Метод проверяет существование аудита по entityType
     */
    @Override
    public boolean checkIfExistsByEntityType(String entityType) {
        return auditRepository.existsByEntityType(entityType);
    }
}
