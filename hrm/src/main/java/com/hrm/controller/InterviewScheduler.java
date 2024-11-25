package com.hrm.controller;
import com.hrm.model.Interviews;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.util.concurrent.*;

public class InterviewScheduler {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void scheduleInterview(Interviews interview) {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDate interviewDate = interview.getInterview_date();
        LocalTime interviewTime = interview.getInterview_time();
        
        // Tính thời gian trì hoãn đến thời điểm phỏng vấn
        long delay = calculateDelayInSeconds(nowDate, nowTime, interviewDate, interviewTime);
        
        // Nếu thời gian phỏng vấn đã qua, không thực hiện
        if (delay < 0) {
            System.out.println("Interview time has already passed!");
            return;
        }

        // Lên lịch cập nhật stage thành "inprogress"
        scheduler.schedule(() -> {
            if ("Pending".equals(interview.getInterview_stage())) {
                interview.setInterview_stage("In progress");
                System.out.println("Interview is in progress...");
            }

            // Trạng thái sẽ giữ nguyên là "inprogress" cho đến khi được cập nhật thành "complete"
            
        }, delay, TimeUnit.SECONDS);
    }

    // Phương thức tính toán thời gian trì hoãn đến thời gian phỏng vấn
    private long calculateDelayInSeconds(LocalDate nowDate, LocalTime nowTime, LocalDate interviewDate, LocalTime interviewTime) {
        if (interviewDate.isBefore(nowDate) || (interviewDate.isEqual(nowDate) && interviewTime.isBefore(nowTime))) {
            return -1; // Nếu phỏng vấn đã qua, trả về -1
        }

        // Tính toán thời gian trì hoãn đến thời điểm phỏng vấn
        Duration dateDuration = Duration.between(nowDate.atStartOfDay(), interviewDate.atStartOfDay());
        Duration timeDuration = Duration.between(nowTime, interviewTime);
        
        return dateDuration.getSeconds() + timeDuration.getSeconds();
    }

    // Phương thức để hoàn thành phỏng vấn, cập nhật stage thành "complete"
    public void completeInterview(Interviews interview) {
        if (interview.getResult().equals("failed") || interview.getResult().equals("passed")) {
            interview.setInterview_stage("Completed");
            System.out.println("Interview is complete.");
        } else {
            System.out.println("Interview is not in progress, cannot complete.");
        }
    }
}
