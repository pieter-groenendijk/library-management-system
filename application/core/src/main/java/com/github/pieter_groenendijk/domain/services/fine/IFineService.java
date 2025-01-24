package com.github.pieter_groenendijk.domain.services.fine;

import com.github.pieter_groenendijk.dto.FineSummaryDTO;

public interface IFineService {
    FineSummaryDTO retrieveUnpaidSummary(Long accountId) throws Exception;
    void payUnpaid(Long accountId) throws Exception;
}
