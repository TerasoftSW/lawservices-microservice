package com.terasoft.servicesbc.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomLegalCaseView {
    @Id @Column(length = 36) @Getter @Setter
    private String customLegalCaseId;
    @Column(length = 20) @Getter @Setter
    private String state;
    @Column(length = 50) @Getter @Setter
    private String title;
    @Column(length = 20) @Getter @Setter
    private String startedAt;
    @Column(length = 20) @Getter @Setter
    private String finishedAt;
    @Column(length = 20) @Getter @Setter
    private String meetType;
    @Column(length = 30) @Getter @Setter
    private String linkZoom;

    public CustomLegalCaseView() {}

    public CustomLegalCaseView(String customLegalCaseId, String state, String title, String startedAt, String finishedAt, String meetType, String linkZoom) {
        this.customLegalCaseId = customLegalCaseId;
        this.state = state;
        this.title = title;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.meetType = meetType;
        this.linkZoom = linkZoom;
    }
}
