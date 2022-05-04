package com.terasoft.servicesbc.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class EditLegalAdviceRequest {
    private @Getter @Setter String legalAdviceId;
    private @Getter String state;
    private @Getter String title;
    private @Getter String description;
    private @Getter String legalResponse;
}
