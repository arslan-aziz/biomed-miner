package com.arslan_aziz.food_for_thought.application;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.arslan_aziz.food_for_thought.service.NlpExtractionService;
import com.arslan_aziz.food_for_thought.service.dto.NlpExtractionDto;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class NlpExtractionController {
	
	private NlpExtractionService nlpExtractionService;
	private Logger logger = LoggerFactory.getLogger(NlpExtractionController.class);
	
	@Autowired
	public NlpExtractionController(NlpExtractionService nlpExtractionService) {
		this.nlpExtractionService = nlpExtractionService;
	}
	
	@PostMapping(value="/nlpextraction", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> requestSearch(
			@RequestParam("querykey") String query,
			@RequestParam("querynodeid") String queryNodeId
		) throws IOException, InterruptedException, SAXException, ParserConfigurationException {
		String queryKey = NlpExtractionService.normalizeQuery(query);

		nlpExtractionService.createNlpExtraction(queryKey, queryNodeId);

		// return queryKey for client to use in follow-on request
		return ResponseEntity.ok(queryKey);
	}
	
	@GetMapping(value="/nlpextraction", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSearchResult(@RequestParam("querykey") String querykey) {
		NlpExtractionDto nlpExtractionDto = nlpExtractionService.getNlpExtractionDtoByKey(querykey);
		if (nlpExtractionDto == null) {
			logger.info("Entity " + querykey + " not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.ok(nlpExtractionDto);
		}
	}

}
