package com.internship.tool.controller;

import com.internship.tool.entity.TrainingRecord;
import com.internship.tool.repository.TrainingRecordRepository;
import com.internship.tool.repository.AuditLogRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainingRecordController.class)
public class TrainingRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingRecordRepository repository;

    @MockBean
    private AuditLogRepository auditRepository;

    // ✅ TEST GET ALL
    @Test
    void testGetAll() throws Exception {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/training"))
                .andExpect(status().isOk());
    }

    // ✅ TEST SEARCH
    @Test
    void testSearch() throws Exception {
        when(repository.findByTitleContainingIgnoreCase("test"))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/training/search?q=test"))
                .andExpect(status().isOk());
    }

    // ✅ TEST STATS
    @Test
    void testStats() throws Exception {
        when(repository.count()).thenReturn(5L);

        mockMvc.perform(get("/api/training/stats"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    // ✅ TEST UPDATE
    @Test
    void testUpdate() throws Exception {

        TrainingRecord record = new TrainingRecord();
        record.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(record));
        when(repository.save(record)).thenReturn(record);

        String body = """
                {
                  "title": "Updated"
                }
                """;

        mockMvc.perform(put("/api/training/1?role=ADMIN")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isOk());
    }

    // ✅ TEST DELETE
    @Test
    void testDelete() throws Exception {

        TrainingRecord record = new TrainingRecord();
        record.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(record));

        mockMvc.perform(delete("/api/training/1?role=ADMIN"))
                .andExpect(status().isOk());
    }
}