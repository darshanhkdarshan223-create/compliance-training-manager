package com.internship.tool.service;

import com.internship.tool.dto.TrainingRequest;
import com.internship.tool.dto.TrainingResponse;
import com.internship.tool.entity.Training;
import com.internship.tool.exception.ResourceNotFoundException;
import com.internship.tool.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository repository;

    public TrainingResponse create(TrainingRequest request) {
        Training training = Training.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .dueDate(request.getDueDate())
                .build();

        Training saved = repository.save(training);
        return mapToResponse(saved);
    }

    public List<TrainingResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TrainingResponse getById(Long id) {
        Training training = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training not found"));

        return mapToResponse(training);
    }

    public TrainingResponse update(Long id, TrainingRequest request) {
        Training training = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training not found"));

        training.setTitle(request.getTitle());
        training.setDescription(request.getDescription());
        training.setStatus(request.getStatus());
        training.setDueDate(request.getDueDate());

        return mapToResponse(repository.save(training));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Training not found");
        }
        repository.deleteById(id);
    }

    private TrainingResponse mapToResponse(Training t) {
        return TrainingResponse.builder()
                .id(t.getId())
                .title(t.getTitle())
                .description(t.getDescription())
                .status(t.getStatus())
                .dueDate(t.getDueDate())
                .createdAt(t.getCreatedAt())
                .build();
    }
}