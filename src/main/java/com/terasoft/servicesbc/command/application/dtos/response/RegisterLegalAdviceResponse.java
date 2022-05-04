package com.terasoft.servicesbc.command.application.dtos.response;

import lombok.Value;

@Value
public class RegisterLegalAdviceResponse {
    private String legalAdviceId;
    private String state;
    private String title;
    private String description;
}
