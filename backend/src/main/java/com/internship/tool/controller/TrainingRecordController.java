package com.internship.tool.controller;

import com.internship.tool.entity.TrainingRecord;
import com.internship.tool.entity.AuditLog;
import com.internship.tool.repository.TrainingRecordRepository;
import com.internship.tool.repository.AuditLogRepository;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.time.LocalDateTime;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

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

    // ✅ GET ALL WITH PAGINATION
    @GetMapping
    public Page<TrainingRecord> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable);
    }

    // PUT {id} → Update
    @PutMapping("/{id}")
    public TrainingRecord update(@PathVariable Long id,
                                 @RequestBody TrainingRecord updated,
                                 @RequestParam(required = false) String role) {

        if (role == null) throw new RuntimeException("Role is required");
        if (!role.equals("ADMIN") && !role.equals("MANAGER"))
            throw new RuntimeException("Access Denied");

        TrainingRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        String oldData = record.toString();

        record.setTitle(updated.getTitle());
        record.setDescription(updated.getDescription());
        record.setStatus(updated.getStatus());
        record.setPriority(updated.getPriority());
        record.setAssignedTo(updated.getAssignedTo());
        record.setDueDate(updated.getDueDate());
        record.setScore(updated.getScore());

        TrainingRecord saved = repository.save(record);

        AuditLog log = new AuditLog();
        log.setEntityType("TrainingRecord");
        log.setEntityId(id);
        log.setAction("UPDATE");
        log.setChangedBy(role);
        log.setChangedAt(LocalDateTime.now());
        log.setOldValue(oldData);
        log.setNewValue(saved.toString());

        auditRepository.save(log);

        return saved;
    }

    // DELETE {id}
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id,
                         @RequestParam(required = false) String role) {

        if (role == null) throw new RuntimeException("Role is required");
        if (!role.equals("ADMIN"))
            throw new RuntimeException("Only ADMIN can delete");

        TrainingRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        String oldData = record.toString();

        record.setStatus("DELETED");
        repository.save(record);

        AuditLog log = new AuditLog();
        log.setEntityType("TrainingRecord");
        log.setEntityId(id);
        log.setAction("DELETE");
        log.setChangedBy(role);
        log.setChangedAt(LocalDateTime.now());
        log.setOldValue(oldData);
        log.setNewValue("DELETED");

        auditRepository.save(log);

        return "Deleted";
    }

    // SEARCH
    @GetMapping("/search")
    public List<TrainingRecord> search(@RequestParam String q) {
        return repository.findByTitleContainingIgnoreCase(q);
    }

    // STATS
    @GetMapping("/stats")
    public long stats() {
        return repository.count();
    }

    // ✅ CSV EXPORT
    @GetMapping("/export")
    public String exportCSV() {

        List<TrainingRecord> records = repository.findAll();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);

        // header
        writer.println("ID,Title,Status,Priority,DueDate");

        for (TrainingRecord r : records) {
            writer.println(
                    r.getId() + "," +
                            r.getTitle() + "," +
                            r.getStatus() + "," +
                            r.getPriority() + "," +
                            r.getDueDate()
            );
        }

        writer.flush();
        return out.toString();
    }
}