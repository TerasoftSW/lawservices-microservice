package com.terasoft.servicesbc.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class EditCustomLegalCaseRequest {
    private @Getter @Setter String customLegalCaseId;
    private @Getter String state;
    private @Getter String title;
    private @Getter String startedAt;
    private @Getter String finishedAt;
    private @Getter String meetType;
    private @Getter String linkZoom;
}
