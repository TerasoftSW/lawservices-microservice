package com.terasoft.servicesbc.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CustomLegalCaseHistoryView {
    @Id @GeneratedValue @Getter @Setter
    private Long customLegalCaseHistoryId;
    @Column(length = 36) @Getter @Setter
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

    public CustomLegalCaseHistoryView() {}

    public CustomLegalCaseHistoryView(String customLegalCaseId, String state, String title, String startedAt, String finishedAt, String meetType, String linkZoom) {
        this.customLegalCaseId = customLegalCaseId;
        this.state = state;
        this.title = title;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.meetType = meetType;
        this.linkZoom = linkZoom;
    }

    public CustomLegalCaseHistoryView(CustomLegalCaseHistoryView customLegalCaseHistoryView) {
        this.customLegalCaseId = customLegalCaseHistoryView.getCustomLegalCaseId();
        this.state = customLegalCaseHistoryView.getState();
        this.title = customLegalCaseHistoryView.getTitle();
        this.startedAt = customLegalCaseHistoryView.getStartedAt();
        this.finishedAt = customLegalCaseHistoryView.getFinishedAt();
        this.meetType = customLegalCaseHistoryView.getMeetType();
        this.linkZoom = customLegalCaseHistoryView.getLinkZoom();
    }
}
