package com.minhnk.batch.config;

import com.minhnk.batch.service.BatchService;
import com.minhnk.batch.tasks.PostTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private BatchService batchService;

    @Bean
    public Step postTask(){
        return steps.get("postTask")
                .tasklet(new PostTask(batchService))
                .build();
    }

    @Bean
    public Job postJob(){
        return jobs.get("postJob")
                .incrementer(new RunIdIncrementer())
                .start(postTask())
                .build();
    }
}
