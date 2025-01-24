package com.github.pieter_groenendijk.domain.services.account;

import com.github.pieter_groenendijk.domain.entities.loan.LoanLimit;
import com.github.pieter_groenendijk.domain.entities.account.Account;
import com.github.pieter_groenendijk.dto.MembershipTypeDTO;
import com.github.pieter_groenendijk.domain.entities.membership.MembershipType;
import com.github.pieter_groenendijk.domain.entities.membership.Membership;
import com.github.pieter_groenendijk.dto.MembershipDTO;

import java.util.List;
import com.github.pieter_groenendijk.dto.AccountDTO;

public interface IAccountService {

    Account retrieveAccountById(long id) throws Exception;
    
    MembershipType retrieveMembershipTypeById(long id);
    
    void store(Account account) throws Exception;

    void store(AccountDTO account) throws Exception;

    void update(Account account) throws Exception;

    void update(long id, AccountDTO account) throws Exception;

    void store(MembershipTypeDTO membershipType);
    
    Membership retrieveMembershipById(long id);
    
    void store(MembershipDTO request) throws Exception;

    List<Membership> retrieveMembershipsByAccountId (long id);

    void setIsBlocked(long id, boolean newValue) throws Exception;

    void update(long id, MembershipTypeDTO request);

    List<MembershipType> retrieveMembershipTypeList();

    void update(long id, MembershipDTO request);

    void softDeleteAccount(long id) throws Exception;

    void softDeleteMembership(long id);

    void softDeleteMembershipType(long id);

    LoanLimit retrieveLendingLimitById(long id);

    void store(LoanLimit loanLimit);

    void update(long id, LoanLimit loanLimit);

    List<LoanLimit> retrieveLendingLimitList(long id);

    void softDeleteLendingLimit(long id);
}
