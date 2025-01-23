package com.github.pieter_groenendijk.service.fine;

import com.github.pieter_groenendijk.model.DTO.FineDTO;
import com.github.pieter_groenendijk.model.fine.Fine;

import java.util.List;

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
