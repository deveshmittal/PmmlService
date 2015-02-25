package com.startapp.pmml.core;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fiestacabin.dropwizard.quartz.Scheduled;

@Scheduled(interval = 5, unit = TimeUnit.SECONDS)
public class PmmlLoaderJob implements Job {

	public PmmlLoaderJob() {
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context);
		System.out.println(LocalDateTime.now());
	}
}
