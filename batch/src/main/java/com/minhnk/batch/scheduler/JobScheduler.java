package com.minhnk.batch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Scheduled(fixedRate = 20000)
    public void run() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("postJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job, params);
    }
}
