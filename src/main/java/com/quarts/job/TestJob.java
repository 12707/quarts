package com.quarts.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class TestJob implements Job {
    Logger logger = getLogger(TestJob.class);
    private int i;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Start TestJob!");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        int j = jobDataMap.getIntValue("j");
        logger.info("j = " + (j++));
        jobDataMap.put("jj", String.valueOf(j));
        logger.info("i = " + (i++));

        int jj = jobDataMap.getIntValue("jj");
        logger.info("jj = " + (jj++));
        logger.info("End TestJob!");
    }
}
