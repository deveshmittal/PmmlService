package com.startapp.pmml;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.fiestacabin.dropwizard.quartz.ManagedScheduler;
import com.google.inject.Injector;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.startapp.pmml.core.PmmlModule;
import com.startapp.pmml.core.schedule.ScheduleModule;

public class PmmlApplication extends Application<PmmlConfiguration> {
	private GuiceBundle<PmmlConfiguration> guiceBundle;

	public PmmlApplication() {
	}

	public static void main(String[] args) throws Exception {
		new PmmlApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<PmmlConfiguration> bootstrap) {
		//@formatter:off
		guiceBundle = GuiceBundle.<PmmlConfiguration> newBuilder()
				.addModule(new ScheduleModule())
				.addModule(new PmmlModule())
				.enableAutoConfig(getClass().getPackage().getName())
				.setConfigClass(PmmlConfiguration.class)
				.build();
		//@formatter:on
		bootstrap.addBundle(guiceBundle);
	}

	@Override
	public void run(PmmlConfiguration configuration, Environment environment) throws Exception {
		Injector injector = guiceBundle.getInjector();
	    environment.lifecycle().manage(injector.getInstance(ManagedScheduler.class));
	}
}
