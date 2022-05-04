package com.terasoft.servicesbc.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LegalAdviceHistoryView {
    @Id @GeneratedValue @Getter @Setter
    private Long legalAdviceHistoryId;
    @Column(length = 36) @Getter @Setter
    private String legalAdviceId;
    @Column(length = 20) @Getter @Setter
    private String state;
    @Column(length = 50) @Getter @Setter
    private String title;
    @Column(length = 50) @Getter @Setter
    private String description;
    @Column(length = 50) @Getter @Setter
    private String legalResponse;

    public LegalAdviceHistoryView() {}

    public LegalAdviceHistoryView(String legalAdviceId, String state, String title, String description, String legalResponse) {
        this.legalAdviceId = legalAdviceId;
        this.state = state;
        this.title = title;
        this.description = description;
        this.legalResponse = legalResponse;
    }

    public LegalAdviceHistoryView(LegalAdviceHistoryView legalAdviceHistoryView) {
        this.legalAdviceId = legalAdviceHistoryView.getLegalAdviceId();
        this.state = legalAdviceHistoryView.getState();
        this.title = legalAdviceHistoryView.getTitle();
        this.description = legalAdviceHistoryView.getDescription();
        this.legalResponse = legalAdviceHistoryView.getLegalResponse();
    }
}
