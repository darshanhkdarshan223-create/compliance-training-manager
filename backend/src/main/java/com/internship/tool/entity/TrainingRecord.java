package com.internship.tool.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "training_records")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "status")
    private String status = "PENDING";

    @Column(name = "compliance_score", precision = 5, scale = 2)
    private BigDecimal complianceScore;

    @Column(name = "priority")
    private String priority;

    @Column(name = "score")
    private Integer score;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "completed_date")
    private LocalDate completedDate;

    @Column(name = "ai_description", columnDefinition = "TEXT")
    private String aiDescription;

    @Column(name = "ai_recommendations", columnDefinition = "TEXT")
    private String aiRecommendations;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;
}