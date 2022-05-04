package com.terasoft.servicesbc.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomLegalCaseHistoryViewRepository extends JpaRepository<CustomLegalCaseHistoryView, String> {
    @Query(value = "SELECT * FROM custom_legal_case_history_view WHERE custom_legal_case_history_id = (SELECT MAX(custom_legal_case_history_id) FROM custom_legal_case_history_view WHERE custom_legal_case_id = :customLegalCaseId)", nativeQuery = true)
    Optional<CustomLegalCaseHistoryView> getLastByCustomLegalCaseId(String customLegalCaseId);

    @Query(value = "SELECT * FROM custom_legal_case_history_view WHERE custom_legal_case_id = :customLegalCaseId", nativeQuery = true)
    List<CustomLegalCaseHistoryView> getHistoryByCustomLegalCaseId(String customLegalCaseId);
}
