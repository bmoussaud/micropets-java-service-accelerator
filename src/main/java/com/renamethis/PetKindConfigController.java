package com.renamethis;

import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PetKindConfigController {

	private static Logger logger = LoggerFactory.getLogger(PetKindConfigController.class);

	private final HikariDataSource dataSource;

	public PetKindConfigController(HikariDataSource dataSource) {
		this.dataSource = dataSource;
	}

	@GetMapping(value = "/lowercasePetKind/v1/config", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, ?> config() {

		logger.debug("configuration the PetKind database");
		return Map.of(
				"datasource.url", dataSource.getJdbcUrl(),
				"datasource.driver", dataSource.getDriverClassName(),
				"kind", "lowercasePetKind");

	}

}