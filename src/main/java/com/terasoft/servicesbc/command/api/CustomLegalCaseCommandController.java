package com.terasoft.servicesbc.command.api;

import com.terasoft.servicesbc.command.application.dtos.request.EditCustomLegalCaseRequest;
import com.terasoft.servicesbc.command.application.dtos.request.RegisterCustomLegalCaseRequest;
import com.terasoft.servicesbc.command.application.dtos.response.EditCustomLegalCaseResponse;
import com.terasoft.servicesbc.command.application.dtos.response.RegisterCustomLegalCaseResponse;
import com.terasoft.servicesbc.command.application.services.CustomLegalCaseApplicationService;
import com.terasoft.servicesbc.common.api.ApiController;
import com.terasoft.servicesbc.common.application.Notification;
import com.terasoft.servicesbc.common.application.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customLegalCases")
@Tag(name = "Custom Legal Cases")
public class CustomLegalCaseCommandController {
    private final CustomLegalCaseApplicationService customLegalCaseApplicationService;
    private final CommandGateway commandGateway;
    public CustomLegalCaseCommandController(CustomLegalCaseApplicationService customLegalCaseApplicationService, CommandGateway commandGateway) {
        this.customLegalCaseApplicationService = customLegalCaseApplicationService;
        this.commandGateway = commandGateway;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Post new Custom Legal Case")
    public ResponseEntity<Object> register(@RequestBody RegisterCustomLegalCaseRequest registerCustomLegalCaseRequest) {
        try {
            Result<RegisterCustomLegalCaseResponse, Notification> result = customLegalCaseApplicationService.register(registerCustomLegalCaseRequest);
            if (result.isSuccess()){
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{customLegalCaseId}")
    @Operation(summary = "Update Custom Legal Case by Id")
    public ResponseEntity<Object> edit(@PathVariable("customLegalCaseId") String customLegalCaseId, @RequestBody EditCustomLegalCaseRequest editCustomLegalCaseRequest) {
        try {
            editCustomLegalCaseRequest.setCustomLegalCaseId(customLegalCaseId);
            Result<EditCustomLegalCaseResponse, Notification> result = customLegalCaseApplicationService.edit(editCustomLegalCaseRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
