package com.internship.tool.controller;

import com.internship.tool.entity.TrainingRecord;
import com.internship.tool.repository.TrainingRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.internship.tool.entity.AuditLog;
import com.internship.tool.repository.AuditLogRepository;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/training")
public class TrainingRecordController {

    private final TrainingRecordRepository repository;
    private final AuditLogRepository auditRepository;

    public TrainingRecordController(TrainingRecordRepository repository,
                                    AuditLogRepository auditRepository) {
        this.repository = repository;
        this.auditRepository = auditRepository;
    }

    // PUT {id} → Update
    @PutMapping("/{id}")
    public TrainingRecord update(@PathVariable Long id,
                                 @RequestBody TrainingRecord updated,
                                 @RequestParam(required = false) String role) {

        if (role == null) {
            throw new RuntimeException("Role is required");
        }

        if (!role.equals("ADMIN") && !role.equals("MANAGER")) {
            throw new RuntimeException("Access Denied");
        }

        TrainingRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        // ✅ Capture OLD data
        String oldData = record.toString();

        // Update fields
        record.setTitle(updated.getTitle());
        record.setDescription(updated.getDescription());
        record.setStatus(updated.getStatus());
        record.setPriority(updated.getPriority());
        record.setAssignedTo(updated.getAssignedTo());
        record.setDueDate(updated.getDueDate());
        record.setScore(updated.getScore());

        TrainingRecord saved = repository.save(record);

        // ✅ Capture NEW data
        String newData = saved.toString();

        // ✅ Audit log
        AuditLog log = new AuditLog();
        log.setEntityType("TrainingRecord");
        log.setEntityId(id);
        log.setAction("UPDATE");
        log.setChangedBy(role);
        log.setChangedAt(LocalDateTime.now());
        log.setOldValue(oldData);
        log.setNewValue(newData);

        auditRepository.save(log);

        return saved;
    }

    // DELETE {id} → Soft delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id,
                         @RequestParam(required = false) String role) {

        if (role == null) {
            throw new RuntimeException("Role is required");
        }

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Only ADMIN can delete");
        }

        TrainingRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        // ✅ Capture OLD data
        String oldData = record.toString();

        record.setStatus("DELETED");
        repository.save(record);

        // ✅ Audit log
        AuditLog log = new AuditLog();
        log.setEntityType("TrainingRecord");
        log.setEntityId(id);
        log.setAction("DELETE");
        log.setChangedBy(role);
        log.setChangedAt(LocalDateTime.now());
        log.setOldValue(oldData);
        log.setNewValue("DELETED");

        auditRepository.save(log);

        return "Record deleted successfully";
    }

    // GET /search?q=
    @GetMapping("/search")
    public List<TrainingRecord> search(@RequestParam String q) {
        return repository.findByTitleContainingIgnoreCase(q);
    }

    // GET /stats
    @GetMapping("/stats")
    public long stats() {
        return repository.count();
    }
}