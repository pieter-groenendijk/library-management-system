package com.github.pieter_groenendijk.domain.services.fine;

import com.github.pieter_groenendijk.dto.fine.FineDTO;
import com.github.pieter_groenendijk.domain.entities.fine.Fine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FineMapper {
    public FineDTO toDTO(Fine fine) {
        FineDTO dto = new FineDTO();

        dto.setFineId(fine.getFineId());
        dto.setFineType(fine.getFineType().getTitle());
        dto.setAmountInCents(fine.getAmountInCents());
        dto.setDeclaredOn(fine.getDeclaredOn());

        return dto;
    }

    public List<FineDTO> toDTO(List<Fine> fines) {
        return fines
            .stream()
            .map(this::toDTO)
            .toList();
    }
}
