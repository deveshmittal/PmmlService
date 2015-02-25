package com.startapp.pmml.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.jpmml.evaluator.Evaluator;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fiestacabin.dropwizard.quartz.Scheduled;
import com.google.inject.Inject;

@Scheduled(interval = 60, unit = TimeUnit.SECONDS)
public class PmmlLoaderJob implements Job {
	private final PmmlLoader pmmlLoader;
	private final PmmlCache pmmlCache;

	@Inject
	public PmmlLoaderJob(PmmlLoader pmmlLoader, PmmlCache pmmlCache) {
		this.pmmlLoader = pmmlLoader;
		this.pmmlCache = pmmlCache;

	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Map<String, List<Evaluator>> loadedEvaluators = pmmlLoader.load();
		pmmlCache.switchEvaluators(loadedEvaluators);
	}
}
