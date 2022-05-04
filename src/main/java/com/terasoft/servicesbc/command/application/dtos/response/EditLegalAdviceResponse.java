package com.terasoft.servicesbc.command.application.dtos.response;


import lombok.Value;

@Value
public class EditLegalAdviceResponse {
    private String legalAdviceId;
    private String state;
    private String title;
    private String description;
    private String legalResponse;
}
