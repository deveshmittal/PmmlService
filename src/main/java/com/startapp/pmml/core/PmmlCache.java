package com.startapp.pmml.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javax.xml.transform.sax.SAXSource;

import org.dmg.pmml.PMML;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.ModelEvaluatorFactory;
import org.jpmml.manager.PMMLManager;
import org.jpmml.model.ImportFilter;
import org.jpmml.model.JAXBUtil;
import org.xml.sax.InputSource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PmmlCache {
	private final Path path;

	public PmmlCache(String pmmlFileLocation) {
		path = FileSystems.getDefault().getPath(pmmlFileLocation);
	}

	public Map<String, List<Evaluator>> getWeightEvaluator() {
		return loadPmmlFiles();
	}

	private Map<String, List<Evaluator>> loadPmmlFiles() {
		int evaluatorInsatncesAmount = 5;
		Map<String, List<Evaluator>> evaluators = Maps.newHashMap();

		File pmmlFolder = path.toFile();
		for (File fileEntry : pmmlFolder.listFiles()) {
			try {
				// PMML pmml = IOUtil.unmarshal(fileEntry);
				InputStream in = new FileInputStream(fileEntry);
				InputSource source = new InputSource(in);

				// Performs on-the-fly conversion from any PMML schema version document to the latest PMML schema
				// version 4.2 document
				SAXSource filteredSource = ImportFilter.apply(source);

				PMML pmml = JAXBUtil.unmarshalPMML(filteredSource);

				/*
				 * LocatorNullifier nullifier = new LocatorNullifier(); nullifier.applyTo(pmml);
				 */

				PMMLManager pmmlManager = new PMMLManager(pmml);
				List<Evaluator> evaluatorInstances = Lists.newArrayList();
				for (int i = 0; i < evaluatorInsatncesAmount; i++) {
					evaluatorInstances.add((Evaluator) pmmlManager.getModelManager(null, ModelEvaluatorFactory.getInstance()));

				}
				// Evaluator evaluator = (Evaluator)pmmlManager.getModelManager(null,
				// ModelEvaluatorFactory.getInstance());
				String groupId = calcGroupId(fileEntry.getName());
				if (groupId != null) {
					evaluators.put(groupId, evaluatorInstances);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return evaluators;
	}

	private String calcGroupId(String fileName) {
		int dotIndex = fileName.indexOf(".");
		if (dotIndex > 0) {
			return fileName.substring(0, dotIndex);
		}
		return null;
	}

}
