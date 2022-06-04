package com.terasoft.servicesbc.command.application.services;


import com.terasoft.common.application.Notification;
import com.terasoft.common.application.Result;
import com.terasoft.common.application.ResultType;
import com.terasoft.common.domain.enums.LawServiceState;
import com.terasoft.servicesbc.command.application.dtos.request.EditLegalAdviceRequest;
import com.terasoft.servicesbc.command.application.dtos.request.RegisterLegalAdviceRequest;
import com.terasoft.servicesbc.command.application.dtos.response.EditLegalAdviceResponse;
import com.terasoft.servicesbc.command.application.dtos.response.RegisterLegalAdviceResponse;
import com.terasoft.servicesbc.command.application.validators.EditLegalAdviceValidator;
import com.terasoft.servicesbc.command.application.validators.RegisterLegalAdviceValidator;
import com.terasoft.servicesbccontracts.commands.EditLegalAdvice;
import com.terasoft.servicesbccontracts.commands.RegisterLegalAdvice;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class LegalAdviceApplicationService {
    private final RegisterLegalAdviceValidator registerLegalAdviceValidator;
    private final EditLegalAdviceValidator editLegalAdviceValidator;
    private final CommandGateway commandGateway;
    public LegalAdviceApplicationService(RegisterLegalAdviceValidator registerLegalAdviceValidator, EditLegalAdviceValidator editLegalAdviceValidator, CommandGateway commandGateway) {
        this.registerLegalAdviceValidator = registerLegalAdviceValidator;
        this.editLegalAdviceValidator = editLegalAdviceValidator;
        this.commandGateway = commandGateway;
    }

    public Result<RegisterLegalAdviceResponse, Notification> register(RegisterLegalAdviceRequest registerLegalAdviceRequest) throws Exception {
        Notification notification = this.registerLegalAdviceValidator.validate(registerLegalAdviceRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String legalAdviceId = UUID.randomUUID().toString();
        RegisterLegalAdvice registerLegalAdvice = new RegisterLegalAdvice(
                legalAdviceId,
                registerLegalAdviceRequest.getTitle().trim(),
                registerLegalAdviceRequest.getDescription().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(registerLegalAdvice);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterLegalAdviceResponse registerLegalAdviceResponse = new RegisterLegalAdviceResponse(
                registerLegalAdvice.getLegalAdviceId(),
                LawServiceState.INPROCESS.toString(),
                registerLegalAdvice.getTitle(),
                registerLegalAdvice.getDescription()
        );
        return  Result.success(registerLegalAdviceResponse);
    }

    public Result<EditLegalAdviceResponse, Notification> edit(EditLegalAdviceRequest editLegalAdviceRequest) throws Exception {
        Notification notification = this.editLegalAdviceValidator.validate(editLegalAdviceRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditLegalAdvice editLegalAdvice = new EditLegalAdvice(
                editLegalAdviceRequest.getLegalAdviceId().trim(),
                LawServiceState.valueOf(editLegalAdviceRequest.getState()),
                editLegalAdviceRequest.getTitle().trim(),
                editLegalAdviceRequest.getDescription().trim(),
                editLegalAdviceRequest.getLegalResponse().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(editLegalAdvice);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditLegalAdviceResponse editLegalAdviceResponse = new EditLegalAdviceResponse(
                editLegalAdvice.getLegalAdviceId(),
                editLegalAdvice.getState().toString(),
                editLegalAdvice.getTitle(),
                editLegalAdvice.getDescription(),
                editLegalAdvice.getLegalResponse()
        );
        return Result.success(editLegalAdviceResponse);
    }
}
