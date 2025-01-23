package com.github.pieter_groenendijk.repository.fine;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.fine.Fine;
import com.github.pieter_groenendijk.model.fine.FineBalance;
import com.github.pieter_groenendijk.model.fine.FineType;

import java.util.List;
import java.util.Optional;

public interface IFineRepository {
    Optional<FineType> retrieveFineType(String title) throws Exception;
    void store(Fine fine) throws Exception;
    Optional<FineBalance> retrieveFineBalance(Account account) throws Exception;
    void payUnpaidFines(long accountId) throws Exception;
    List<Fine> retrieveUnpaidFines(Long accountId) throws Exception;
}