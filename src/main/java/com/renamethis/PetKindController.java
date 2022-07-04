package com.renamethis;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PetKindController {

	@Autowired
	PetKindRepository repository;

	@GetMapping(value = "/liveness")
	public String liveness() {
		return "PetKindServiceLiveness";
	}

	@GetMapping(value = "/readiness")
	public String readiness() {
		return "PetKindServiceReadiness";
	}

	@GetMapping(value = { "", "/", "/PetKinds/v1/data" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public PetKindSummary PetKinds() {
		PetKindSummary summary = new PetKindSummary();
		try {
			if (repository.count() == 0) {
				return this.load();
			} else {
				for (PetKind PetKind : repository.findAll()) {
					summary.addPetKind(PetKind);
				}
			}
		} catch (Exception e) {
			return this.load();
		}

		return summary.filter();
	}

	@GetMapping(value = "/PetKinds/v1/data/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PetKind PetKind(@PathVariable Long index) {
		return repository.findById(index).get();
	}

	@GetMapping(value = "/PetKinds/v1/load", produces = MediaType.APPLICATION_JSON_VALUE)
	public PetKindSummary load() {

		repository.save(new PetKind("Tweety", "Yellow Canary", 2,
				"https://upload.wikimedia.org/wikipedia/en/0/02/Tweety.svg"));
		repository.save(new PetKind("Hector", "African Grey Parrot", 5,
				"https://petkeen.com/wp-content/uploads/2020/11/African-Grey-Parrot.webp"));
		repository.save(new PetKind("Micheline", "Budgerigar", 3,
				"https://petkeen.com/wp-content/uploads/2020/11/Budgerigar.webp"));

		repository.save(new PetKind("Piplette", "Cockatoo", 1,
				"https://petkeen.com/wp-content/uploads/2020/11/Cockatoo.webp"));
		return this.PetKinds();
	}

}