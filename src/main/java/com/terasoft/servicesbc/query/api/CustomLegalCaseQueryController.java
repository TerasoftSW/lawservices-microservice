package com.terasoft.servicesbc.query.api;

import com.terasoft.servicesbc.query.projections.CustomLegalCaseHistoryView;
import com.terasoft.servicesbc.query.projections.CustomLegalCaseHistoryViewRepository;
import com.terasoft.servicesbc.query.projections.CustomLegalCaseView;
import com.terasoft.servicesbc.query.projections.CustomLegalCaseViewRepository;
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
@RequestMapping("/services/customLegalCases")
@Tag(name = "Custom Legal Cases")
public class CustomLegalCaseQueryController {
    private final CustomLegalCaseViewRepository customLegalCaseViewRepository;
    private final CustomLegalCaseHistoryViewRepository customLegalCaseHistoryViewRepository;

    public CustomLegalCaseQueryController(CustomLegalCaseViewRepository customLegalCaseViewRepository, CustomLegalCaseHistoryViewRepository customLegalCaseHistoryViewRepository) {
        this.customLegalCaseViewRepository = customLegalCaseViewRepository;
        this.customLegalCaseHistoryViewRepository = customLegalCaseHistoryViewRepository;
    }

    @GetMapping("")
    @Operation(summary = "Get all Custom Legal Cases")
    public ResponseEntity<List<CustomLegalCaseView>> getAll() {
        try {
            return new ResponseEntity<List<CustomLegalCaseView>>(customLegalCaseViewRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Custom Legal Case by id")
    public ResponseEntity<CustomLegalCaseView> getById(@PathVariable("id") String id) {
        try {
            Optional<CustomLegalCaseView> customLegalCaseViewOptional = customLegalCaseViewRepository.findById(id);
            if (customLegalCaseViewOptional.isPresent()) {
                return new ResponseEntity<CustomLegalCaseView>(customLegalCaseViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @Operation(summary = "Get Custom Legal Case history")
    public ResponseEntity<List<CustomLegalCaseHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<CustomLegalCaseHistoryView> customLegalCases = customLegalCaseHistoryViewRepository.getHistoryByCustomLegalCaseId(id);
            return new ResponseEntity<List<CustomLegalCaseHistoryView>>(customLegalCases, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
