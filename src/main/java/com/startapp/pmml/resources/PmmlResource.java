package com.startapp.pmml.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jpmml.evaluator.Evaluator;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.startapp.pmml.core.PmmlCache;

@Path("/pmml")
public class PmmlResource {
	private final PmmlCache pmmlCache;

	@Inject
	public PmmlResource(PmmlCache pmmlCache) {
		this.pmmlCache = pmmlCache;
	}
	
	@GET
	@Timed
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get")
	public Map<String, List<Evaluator>> weightEvaluator() {
		return pmmlCache.getWeightEvaluator();
	}

}
