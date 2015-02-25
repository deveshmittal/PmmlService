package com.startapp.pmml.core.schedule;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class ScheduleModule extends AbstractModule {

	public ScheduleModule() {
	}

	@Override
	protected void configure() {
		// we don't even need this because we scan all project
//		bind(SchedulerConfiguration.class).toInstance(new SchedulerConfiguration(PmmlLoaderJob.class.getPackage().getName()));
//		bind(ManagedScheduler.class);
	}

	@Provides
	@Singleton
	private Scheduler provideScheduler() throws SchedulerException {
		return StdSchedulerFactory.getDefaultScheduler();
	}
}
