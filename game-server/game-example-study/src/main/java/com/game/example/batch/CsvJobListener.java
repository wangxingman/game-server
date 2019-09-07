package com.game.example.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:03 2019/9/6 0006
 * @explain : 监听器
 */
public class CsvJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            System.out.println("批处理执行开始....");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("批处理执行结束....");
        }
    }
}
