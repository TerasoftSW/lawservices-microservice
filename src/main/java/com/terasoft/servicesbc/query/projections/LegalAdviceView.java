package com.terasoft.servicesbc.query.projections;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LegalAdviceView {
    @Id
    @Column(length = 36) @Getter
    @Setter
    private String legalAdviceId;
    @Column(length = 20) @Getter @Setter
    private String state;
    @Column(length = 50) @Getter @Setter
    private String title;
    @Column(length = 50) @Getter @Setter
    private String description;
    @Column(length = 50) @Getter @Setter
    private String legalResponse;

    public LegalAdviceView() {}

    public LegalAdviceView(String legalAdviceId, String state, String title, String description, String legalResponse) {
        this.legalAdviceId = legalAdviceId;
        this.state = state;
        this.title = title;
        this.description = description;
        this.legalResponse = legalResponse;
    }
}
