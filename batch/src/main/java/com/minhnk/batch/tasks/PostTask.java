package com.minhnk.batch.tasks;

import com.minhnk.batch.service.BatchService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.util.Date;

public class PostTask implements Tasklet {

    private BatchService batchService;

    public PostTask(BatchService batchService){
        this.batchService = batchService;
    }

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        System.out.println("PostTask start..");
        System.out.println("................");
        System.out.println("................");
        System.out.println(new Date());
        batchService.getAllPostWithComments();
        System.out.println("................");
        System.out.println("................");
        System.out.println("PostTask done..");

        return RepeatStatus.FINISHED;
    }
}
