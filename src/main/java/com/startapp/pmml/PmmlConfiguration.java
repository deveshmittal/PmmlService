package com.startapp.pmml;

import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PmmlConfiguration extends Configuration {
	@NotEmpty
	@JsonProperty
	private String pmmlFilesLocation;

	public PmmlConfiguration() {
	}

	public String getPmmlFilesLocation() {
		return pmmlFilesLocation;
	}

	public void setPmmlFilesLocation(String pmmlFilesLocation) {
		this.pmmlFilesLocation = pmmlFilesLocation;
	}
}
