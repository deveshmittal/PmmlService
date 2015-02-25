package com.startapp.pmml.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.startapp.pmml.core.PmmlCache;

public class CacheFullHealth extends HealthCheck {
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

}
