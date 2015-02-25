package com.startapp.pmml.core;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.startapp.pmml.PmmlConfiguration;

public class PmmlModule extends AbstractModule {

	public PmmlModule() {
	}

	@Override
	protected void configure() {
	}

	@Provides
	@Named("pmmlFilesLocation")
	public String provideTemplate(PmmlConfiguration configuration) {
		return configuration.getPmmlFileLocation();
	}
}
