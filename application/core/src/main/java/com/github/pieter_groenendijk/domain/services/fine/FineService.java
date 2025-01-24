package com.github.pieter_groenendijk.domain.services.fine;

import com.github.pieter_groenendijk.shared.dto.fine.FineDTO;
import com.github.pieter_groenendijk.shared.dto.fine.FineSummaryDTO;
import com.github.pieter_groenendijk.repositories.fine.IFineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FineService implements IFineService {
    private final IFineRepository REPOSITORY;
    private final FineMapper MAPPER;

    public FineService(
        IFineRepository repository,
        FineMapper mapper
    ) {
        this.REPOSITORY = repository;
        this.MAPPER = mapper;
    }

    @Override
    public FineSummaryDTO retrieveUnpaidSummary(Long accountId) throws Exception {
        FineSummaryDTO summary = new FineSummaryDTO();

        List<FineDTO> fines = this.MAPPER.toDTO(
            this.REPOSITORY.retrieveUnpaidFines(
                accountId
            )
        );

        summary.setFines(fines);
        summary.setTotal(
            fines
                .stream()
                .mapToLong(FineDTO::getAmountInCents)
                .sum()
        );

        return summary;
    }

    @Override
    public void payUnpaid(Long accountId) throws Exception {
        this.REPOSITORY.payUnpaidFines(accountId);
    }
}
