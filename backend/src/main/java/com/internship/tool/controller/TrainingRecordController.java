package com.internship.tool.controller;

import com.internship.tool.entity.TrainingRecord;
import com.internship.tool.repository.TrainingRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingRecordController {

    private final TrainingRecordRepository repository;

    public TrainingRecordController(TrainingRecordRepository repository) {
        this.repository = repository;
    }

    // PUT {id} → Update
    @PutMapping("/{id}")
    public TrainingRecord update(@PathVariable Long id, @RequestBody TrainingRecord updated) {
        TrainingRecord record = repository.findById(id).orElseThrow();

        record.setTitle(updated.getTitle());
        record.setDescription(updated.getDescription());
        record.setStatus(updated.getStatus());
        record.setPriority(updated.getPriority());
        record.setAssignedTo(updated.getAssignedTo());
        record.setDueDate(updated.getDueDate());
        record.setScore(updated.getScore());

        return repository.save(record);
    }

    // DELETE {id} → Soft delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        TrainingRecord record = repository.findById(id).orElseThrow();
        record.setStatus("DELETED");
        repository.save(record);
        return "Deleted";
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