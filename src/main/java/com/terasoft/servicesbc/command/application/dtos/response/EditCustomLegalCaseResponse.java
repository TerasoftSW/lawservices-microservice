package com.terasoft.servicesbc.command.application.dtos.response;

import lombok.Value;

@Value
public class EditCustomLegalCaseResponse {
    private String customLegalCaseId;
    private String state;
    private String title;
    private String startedAt;
    private String finishedAt;
    private String meetType;
    private String linkZoom;
}
