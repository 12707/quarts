package com.quarts.main;

import com.quarts.job.TestJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.slf4j.LoggerFactory.getLogger;

public class QuartsTestMain {
    private static Logger logger = getLogger(QuartsTestMain.class);

    public static void main(String[] args){
        logger.info("Quarts travel starts！");
        //StdSchedulerFactory schedulerFactory = ;
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail jobDetail = newJob(TestJob.class).withIdentity("job1", "group1").build();
            jobDetail.getJobDataMap().put("j", 0);
            Trigger trigger = newTrigger().withIdentity("trigger1", "group2").startNow().withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            logger.info("Sleep for 5 seconds so that job can be completed!");
//            Thread.sleep(5000);
//            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        logger.info("Quarts travel ends！");
    }
}
