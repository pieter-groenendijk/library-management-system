package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.domain.entities.loan.LoanLimit;
import com.github.pieter_groenendijk.domain.entities.account.Account;
import com.github.pieter_groenendijk.dto.MembershipTypeRequestDTO;
import com.github.pieter_groenendijk.domain.entities.membership.MembershipType;
import com.github.pieter_groenendijk.domain.entities.membership.Membership;
import com.github.pieter_groenendijk.dto.MembershipRequestDTO;

import java.util.List;
import com.github.pieter_groenendijk.dto.AccountRequestDTO;

public interface IAccountService {

    Account retrieveAccountById(long id) throws Exception;
    
    MembershipType retrieveMembershipTypeById(long id);
    
    void store(Account account) throws Exception;

    void store(AccountRequestDTO account) throws Exception;

    void update(Account account) throws Exception;

    void update(long id, AccountRequestDTO account) throws Exception;

    void store(MembershipTypeRequestDTO membershipType);
    
    Membership retrieveMembershipById(long id);
    
    void store(MembershipRequestDTO request) throws Exception;

    List<Membership> retrieveMembershipsByAccountId (long id);

    void setIsBlocked(long id, boolean newValue) throws Exception;

    void update(long id, MembershipTypeRequestDTO request);

    List<MembershipType> retrieveMembershipTypeList();

    void update(long id, MembershipRequestDTO request);

    void softDeleteAccount(long id) throws Exception;

    void softDeleteMembership(long id);

    void softDeleteMembershipType(long id);

    LoanLimit retrieveLendingLimitById(long id);

    void store(LoanLimit loanLimit);

    void update(long id, LoanLimit loanLimit);

    List<LoanLimit> retrieveLendingLimitList(long id);

    void softDeleteLendingLimit(long id);
}
