package com.terasoft.servicesbc.query.api;

import com.terasoft.servicesbc.command.domain.entities.LegalAdvice;
import com.terasoft.servicesbc.query.projections.LegalAdviceHistoryView;
import com.terasoft.servicesbc.query.projections.LegalAdviceHistoryViewRepository;
import com.terasoft.servicesbc.query.projections.LegalAdviceView;
import com.terasoft.servicesbc.query.projections.LegalAdviceViewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/legalAdvices")
@Tag(name = "Legal Advices")
public class LegalAdviceQueryController {
    private final LegalAdviceViewRepository legalAdviceViewRepository;
    private final LegalAdviceHistoryViewRepository legalAdviceHistoryViewRepository;

    public LegalAdviceQueryController(LegalAdviceViewRepository legalAdviceViewRepository, LegalAdviceHistoryViewRepository legalAdviceHistoryViewRepository) {
        this.legalAdviceViewRepository = legalAdviceViewRepository;
        this.legalAdviceHistoryViewRepository = legalAdviceHistoryViewRepository;
    }

    @GetMapping("")
    @Operation(summary = "Get all Legal Advices")
    public ResponseEntity<List<LegalAdviceView>> getAll() {
        try {
            return new ResponseEntity<List<LegalAdviceView>>(legalAdviceViewRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Legal Advice by id")
    public ResponseEntity<LegalAdviceView> getById(@PathVariable("id") String id) {
        try {
            Optional<LegalAdviceView> legalAdviceViewOptional = legalAdviceViewRepository.findById(id);
            if (legalAdviceViewOptional.isPresent()) {
                return new ResponseEntity<LegalAdviceView>(legalAdviceViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @Operation(summary = "Get Legal Advice history")
    public ResponseEntity<List<LegalAdviceHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<LegalAdviceHistoryView> legalAdvices = legalAdviceHistoryViewRepository.getHistoryByLegalAdviceId(id);
            return new ResponseEntity<List<LegalAdviceHistoryView>>(legalAdvices, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
