package com.terasoft.servicesbc.command.api;

import com.terasoft.common.api.ApiController;
import com.terasoft.common.application.Notification;
import com.terasoft.common.application.Result;
import com.terasoft.servicesbc.command.application.dtos.request.EditLegalAdviceRequest;
import com.terasoft.servicesbc.command.application.dtos.request.RegisterLegalAdviceRequest;
import com.terasoft.servicesbc.command.application.dtos.response.EditLegalAdviceResponse;
import com.terasoft.servicesbc.command.application.dtos.response.RegisterLegalAdviceResponse;
import com.terasoft.servicesbc.command.application.services.LegalAdviceApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services/legalAdvices")
@Tag(name = "Legal Advices")
public class LegalAdviceCommandController {
    private final LegalAdviceApplicationService  legalAdviceApplicationService;
    private final CommandGateway commandGateway;
    public LegalAdviceCommandController(LegalAdviceApplicationService legalAdviceApplicationService, CommandGateway commandGateway) {
        this.legalAdviceApplicationService = legalAdviceApplicationService;
        this.commandGateway = commandGateway;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Post new Legal Advice")
    public ResponseEntity<Object> register(@RequestBody RegisterLegalAdviceRequest registerLawServiceRequest) {
        try {
            Result<RegisterLegalAdviceResponse, Notification> result = legalAdviceApplicationService.register(registerLawServiceRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{legalAdviceId}")
    @Operation(summary = "Update Legal Advice by Id")
    public ResponseEntity<Object> edit(@PathVariable("legalAdviceId") String legalAdviceId, @RequestBody EditLegalAdviceRequest editLegalAdviceRequest) {
        try {
            editLegalAdviceRequest.setLegalAdviceId(legalAdviceId);
            Result<EditLegalAdviceResponse, Notification> result = legalAdviceApplicationService.edit(editLegalAdviceRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.ok(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
