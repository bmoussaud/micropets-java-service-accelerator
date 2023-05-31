package com.renamethis;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PetKindController {

	static Logger logger = LoggerFactory.getLogger(PetKindController.class);

	@Autowired
	PetKindRepository repository;

	@Autowired
	PetKindGenerator generator;

	@GetMapping(value = "/liveness")
	public String liveness() {
		return "PetKindServiceLiveness";
	}

	@GetMapping(value = "/readiness")
	public String readiness() {
		return "PetKindServiceReadiness";
	}

	@GetMapping(value = { "", "/", "/lowercasePetKind/v1/data" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public PetKindSummary PetKinds() {
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

		return summary.filter();
	}

	@GetMapping(value = "/lowercasePetKind/v1/data/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PetKindBean PetKind(@PathVariable Long index) {
		return repository.findById(index).get();
	}

	@GetMapping(value = "/lowercasePetKind/v1/load", produces = MediaType.APPLICATION_JSON_VALUE)
	public PetKindSummary load() {

		logger.debug("----LOAD....");
		PetKindSummary summary = generator.generate();
		logger.debug("Save All : Insert in db:" + summary.pets);
		repository.saveAll(summary.pets);
		return summary;

	}

}