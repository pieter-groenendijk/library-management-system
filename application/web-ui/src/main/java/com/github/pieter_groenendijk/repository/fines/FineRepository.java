package com.github.pieter_groenendijk.repository.fines;

import com.github.pieter_groenendijk.model.fine.FineSummary;
import com.github.pieter_groenendijk.repository.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class FineRepository extends Repository {
    private final String BASE_PATH = "/account/{accountId}/fines";

    public FineRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public Optional<FineSummary> retrieveUnpaidFinesSummary(Long accountId) {
        return super.retrieveSingle(
            this.BASE_PATH + "/unpaid-summary",
            FineSummary.class,
            accountId
        );
    }
}
