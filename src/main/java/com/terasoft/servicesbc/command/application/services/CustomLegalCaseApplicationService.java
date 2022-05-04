package com.terasoft.servicesbc.command.application.services;

import com.terasoft.servicesbc.command.application.dtos.request.EditCustomLegalCaseRequest;
import com.terasoft.servicesbc.command.application.dtos.request.EditLegalAdviceRequest;
import com.terasoft.servicesbc.command.application.dtos.request.RegisterCustomLegalCaseRequest;
import com.terasoft.servicesbc.command.application.dtos.request.RegisterLegalAdviceRequest;
import com.terasoft.servicesbc.command.application.dtos.response.EditCustomLegalCaseResponse;
import com.terasoft.servicesbc.command.application.dtos.response.EditLegalAdviceResponse;
import com.terasoft.servicesbc.command.application.dtos.response.RegisterCustomLegalCaseResponse;
import com.terasoft.servicesbc.command.application.dtos.response.RegisterLegalAdviceResponse;
import com.terasoft.servicesbc.command.application.validators.EditCustomLegalCaseValidator;
import com.terasoft.servicesbc.command.application.validators.RegisterCustomLegalCaseValidator;
import com.terasoft.servicesbc.command.domain.enums.LawServiceState;
import com.terasoft.servicesbc.command.domain.enums.MeetType;
import com.terasoft.servicesbc.common.application.Notification;
import com.terasoft.servicesbc.common.application.Result;
import com.terasoft.servicesbc.common.application.ResultType;
import com.terasoft.servicesbc.contracts.commands.EditCustomLegalCase;
import com.terasoft.servicesbc.contracts.commands.RegisterCustomLegalCase;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class CustomLegalCaseApplicationService {
    private final RegisterCustomLegalCaseValidator registerCustomLegalCaseValidator;
    private final EditCustomLegalCaseValidator editCustomLegalCaseValidator;
    private final CommandGateway commandGateway;
    public CustomLegalCaseApplicationService(RegisterCustomLegalCaseValidator registerCustomLegalCaseValidator, EditCustomLegalCaseValidator editCustomLegalCaseValidator, CommandGateway commandGateway) {
        this.registerCustomLegalCaseValidator = registerCustomLegalCaseValidator;
        this.editCustomLegalCaseValidator = editCustomLegalCaseValidator;
        this.commandGateway = commandGateway;
    }
    public Result<RegisterCustomLegalCaseResponse, Notification> register(RegisterCustomLegalCaseRequest registerCustomLegalCaseRequest) throws Exception {
        Notification notification = this.registerCustomLegalCaseValidator.validate(registerCustomLegalCaseRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String customLegalCaseId = UUID.randomUUID().toString();
        RegisterCustomLegalCase registerCustomLegalCase = new RegisterCustomLegalCase(
                customLegalCaseId,
                registerCustomLegalCaseRequest.getTitle().trim(),
                registerCustomLegalCaseRequest.getStartedAt().trim(),
                registerCustomLegalCaseRequest.getFinishedAt().trim(),
                MeetType.valueOf(registerCustomLegalCaseRequest.getMeetType()),
                registerCustomLegalCaseRequest.getLinkZoom().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(registerCustomLegalCase);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterCustomLegalCaseResponse registerCustomLegalCaseResponse = new RegisterCustomLegalCaseResponse(
                registerCustomLegalCase.getCustomLegalCaseId(),
                registerCustomLegalCase.getTitle(),
                LawServiceState.INPROCESS.toString(),
                registerCustomLegalCase.getStartedAt(),
                registerCustomLegalCase.getFinishedAt(),
                registerCustomLegalCase.getMeetType().toString(),
                registerCustomLegalCase.getLinkZoom()
        );
        return  Result.success(registerCustomLegalCaseResponse);
    }

    public Result<EditCustomLegalCaseResponse, Notification> edit(EditCustomLegalCaseRequest editCustomLegalCaseRequest) throws Exception {
        Notification notification = this.editCustomLegalCaseValidator.validate(editCustomLegalCaseRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditCustomLegalCase editCustomLegalCase = new EditCustomLegalCase(
                editCustomLegalCaseRequest.getCustomLegalCaseId().trim(),
                LawServiceState.valueOf(editCustomLegalCaseRequest.getState()),
                editCustomLegalCaseRequest.getTitle().trim(),
                editCustomLegalCaseRequest.getStartedAt().trim(),
                editCustomLegalCaseRequest.getFinishedAt().trim(),
                MeetType.valueOf(editCustomLegalCaseRequest.getMeetType()),
                editCustomLegalCaseRequest.getLinkZoom().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(editCustomLegalCase);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditCustomLegalCaseResponse editCustomLegalCaseResponse = new EditCustomLegalCaseResponse(
                editCustomLegalCase.getCustomLegalCaseId(),
                editCustomLegalCase.getState().toString(),
                editCustomLegalCase.getTitle(),
                editCustomLegalCase.getStartedAt(),
                editCustomLegalCase.getFinishedAt(),
                editCustomLegalCase.getMeetType().toString(),
                editCustomLegalCase.getLinkZoom()
        );
        return Result.success(editCustomLegalCaseResponse);
    }
}
