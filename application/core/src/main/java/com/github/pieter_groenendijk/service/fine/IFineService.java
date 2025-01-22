package com.github.pieter_groenendijk.service.fine;

import com.github.pieter_groenendijk.model.DTO.FineSummaryDTO;

public interface IFineService {
    FineSummaryDTO retrieveUnpaidFinesSummary(Long accountId) throws Exception;
}
