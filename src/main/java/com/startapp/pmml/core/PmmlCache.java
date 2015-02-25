package com.startapp.pmml.core;

import java.util.List;
import java.util.Map;

import org.jpmml.evaluator.Evaluator;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;

@Singleton
public class PmmlCache {
	private volatile Map<String, List<Evaluator>> evaluators;

	public PmmlCache() {
		evaluators = Maps.newHashMap();
	}

	public Map<String, List<Evaluator>> getWeightEvaluator() {
		return evaluators;
	}
	
	void switchEvaluators(Map<String, List<Evaluator>> newEvaluators) {
		System.out.println("switching evaluators...");
		evaluators = newEvaluators;
	}
}
