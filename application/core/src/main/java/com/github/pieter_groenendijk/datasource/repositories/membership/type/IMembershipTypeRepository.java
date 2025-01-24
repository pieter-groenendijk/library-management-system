package com.github.pieter_groenendijk.datasource.repositories.membership.type;

import com.github.pieter_groenendijk.domain.entities.loan.LoanLimit;
import com.github.pieter_groenendijk.domain.entities.membership.MembershipType;
import java.util.Optional;
import java.util.List;

public interface IMembershipTypeRepository {
    Optional<MembershipType> retrieveMembershipTypeById(long id);
    void store(MembershipType membershipType);
    boolean doesMembershipTypeExistByDescription(String description);
    void update(MembershipType membershipType);
    List<MembershipType> retrieveMembershipTypeList();
    Optional<LoanLimit> retrieveLendingLimitById(long id);
    void store(LoanLimit loanLimit);
    void update(LoanLimit loanLimit);
    List<LoanLimit> retrieveLendingLimitList(long id);
    int retrieveLendingLimitByGenreAndMembershipType(long membershipTypeId, long genreId);
}