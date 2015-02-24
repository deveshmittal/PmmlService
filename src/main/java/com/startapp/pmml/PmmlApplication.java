package com.startapp.pmml;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.startapp.pmml.core.PmmlCache;
import com.startapp.pmml.resources.PmmlResource;

public class PmmlApplication extends Application<PmmlConfiguration> {

	public PmmlApplication() {
	}
	
	public static void main(String[] args) throws Exception {
		new PmmlApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<PmmlConfiguration> bootstrap) {
		
	}

	@Override
	public void run(PmmlConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(new PmmlResource(new PmmlCache("C:\\work\\projects\\PmmlHandler\\src\\main\\resources\\pmml")));
	}
}
