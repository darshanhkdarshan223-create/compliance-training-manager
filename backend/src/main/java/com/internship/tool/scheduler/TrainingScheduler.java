package com.internship.tool.scheduler;

import com.internship.tool.entity.TrainingRecord;
import com.internship.tool.repository.TrainingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TrainingScheduler {

    private final TrainingRecordRepository repository;

    // DAILY OVERDUE (9 AM)
    @Scheduled(cron = "0 0 9 * * ?")
    public void checkOverdueTasks() {

        LocalDate today = LocalDate.now();
        List<TrainingRecord> overdue = repository.findByDueDateBefore(today);

        overdue.forEach(r ->
                System.out.println("OVERDUE: " + r.getTitle())
        );
    }

    // 7-DAY ALERT (10 AM)
    @Scheduled(cron = "0 0 10 * * ?")
    public void upcomingTasks() {

        LocalDate today = LocalDate.now();
        LocalDate next7 = today.plusDays(7);

        List<TrainingRecord> list =
                repository.findByDueDateBetween(today, next7);

        list.forEach(r ->
                System.out.println("UPCOMING: " + r.getTitle())
        );
    }

    // WEEKLY SUMMARY (Monday 8 AM)
    @Scheduled(cron = "0 0 8 ? * MON")
    public void weeklySummary() {

        long total = repository.count();
        System.out.println("TOTAL RECORDS: " + total);
    }
}