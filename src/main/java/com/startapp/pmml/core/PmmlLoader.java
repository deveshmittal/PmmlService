package com.startapp.pmml.core;

import java.util.List;
import java.util.Map;

import org.jpmml.evaluator.Evaluator;

import com.google.inject.ImplementedBy;

@ImplementedBy(PmmlFileLoader.class)
public interface PmmlLoader {
	Map<String, List<Evaluator>> load();
}
