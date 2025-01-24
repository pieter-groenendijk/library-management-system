package com.github.pieter_groenendijk.repositories.fines;

import com.github.pieter_groenendijk.model.fine.FineSummary;
import com.github.pieter_groenendijk.repositories.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class FineRepository extends Repository {
    private final String BASE_PATH = "/account/{accountId}/fines";

    public FineRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public Optional<FineSummary> retrieveUnpaidFinesSummary(Long accountId) {
        return super.getSingle(
            this.BASE_PATH + "/unpaid-summary",
            FineSummary.class,
            accountId
        );
    }

    public void payUnpaidFines(Long accountId) {
        super.postSingle(
            this.BASE_PATH + "/pay",
            accountId
        );
    }
}
