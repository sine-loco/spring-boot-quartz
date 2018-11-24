package ru.snm.misc.quartz;

import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

import static org.quartz.JobBuilder.newJob;

/**
 * @author sine-loco
 */
@Component
public class SchedulerService {
    private Scheduler scheduler;

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void createSchedule(String cronExpression) throws SchedulerException {
        JobKey jobKey = new JobKey("EmailJob", "Let's Spam everyone");

        // fixme schedule with required timeouts
        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger-" + "EmailJob", "Let's Spam everyone")
                .startNow()
                .withSchedule(scheduleBuilder)
                .build();
        JobDetail jobDetail = newJob(EmailJob.class).withIdentity(jobKey)
                .usingJobData("Job-Id", "1")
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
    @PreDestroy
    public void preDestroy() throws SchedulerException {
        scheduler.shutdown(true);
    }
}