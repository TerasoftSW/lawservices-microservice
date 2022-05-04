package com.terasoft.servicesbc.command.application.dtos.request;

import lombok.Value;

@Value
public class RegisterCustomLegalCaseRequest {
    private String title;
    private String startedAt;
    private String finishedAt;
    private String meetType;
    private String linkZoom;
}
