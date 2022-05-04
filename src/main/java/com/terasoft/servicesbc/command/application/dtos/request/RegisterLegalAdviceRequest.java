package com.terasoft.servicesbc.command.application.dtos.request;

import lombok.Value;

@Value
public class RegisterLegalAdviceRequest {
    private String title;
    private String description;
}
