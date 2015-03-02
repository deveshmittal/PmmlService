package com.startapp.pmml.health;

import com.google.inject.Inject;
import com.hubspot.dropwizard.guice.InjectableHealthCheck;
import com.startapp.pmml.core.PmmlCache;

public class CacheFullHealth extends InjectableHealthCheck {
	private final PmmlCache pmmlCache;

	@Inject
	public CacheFullHealth(PmmlCache pmmlCache) {
		this.pmmlCache = pmmlCache;
	}

	@Override
	protected Result check() throws Exception {
		if (pmmlCache.getWeightEvaluator().size() > 0) {
			return Result.healthy();
		} else {
			return Result.unhealthy("PMML cache is empty");
		}
	}

	@Override
	public String getName() {
		return "pmmlCache";
	}
}
