package com.renamethis;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PetKindController {

	private static Logger logger = LoggerFactory.getLogger(PetKindController.class);

	private final Supplier<Long> latency = () -> new Random().nextLong(500);

	@Autowired
	PetKindRepository repository;

	@Autowired
	PetKindGenerator generator;

	@GetMapping(value = "/liveness")
	public String liveness() {
		logger.debug("liveness");
		return "PetKindServiceLiveness";
	}

	@GetMapping(value = "/readiness")
	public String readiness() {
		logger.debug("readiness");
		return "PetKindServiceReadiness";
	}

	@GetMapping(value = { "", "/", "/lowercasePetKind/v1/data" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public PetKindSummary PetKinds() {

		logger.debug("search all PetKinds");

		PetKindSummary summary = new PetKindSummary();
		try {
			if (repository.count() == 0) {
				return this.load();
			} else {
				for (PetKindBean PetKind : repository.findAll()) {
					summary.addPet(PetKind);
				}
			}
		} catch (Exception e) {
			return this.load();
		}
		waitABit();
		return summary.filter();
	}

	private void waitABit() {
		try {
			long l = latency.get().longValue();
			logger.debug("wait a bit " + l + "ms");
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@GetMapping(value = "/lowercasePetKind/v1/data/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PetKindBean PetKind(@PathVariable Long index) {
		logger.debug("get PetKind by Index " + index);
		return repository.findById(index).get();
	}

	@GetMapping(value = "/lowercasePetKind/v1/load", produces = MediaType.APPLICATION_JSON_VALUE)
	public PetKindSummary load() {

		logger.debug("load values in the PetKind database");
		PetKindSummary summary = generator.generate();
		logger.debug("Save All : Insert in db:" + summary.pets);
		repository.saveAll(summary.pets);
		return summary;

	}

}