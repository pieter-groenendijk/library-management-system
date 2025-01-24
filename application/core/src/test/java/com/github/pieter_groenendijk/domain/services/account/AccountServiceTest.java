package com.github.pieter_groenendijk.domain.services.account;

import com.github.pieter_groenendijk.datasource.repositories.account.IAccountRepository;
import com.github.pieter_groenendijk.datasource.repositories.membership.IMembershipRepository;
import com.github.pieter_groenendijk.datasource.repositories.membership.MembershipRepository;
import com.github.pieter_groenendijk.datasource.repositories.membership.type.IMembershipTypeRepository;
import com.github.pieter_groenendijk.datasource.repositories.membership.type.MembershipTypeRepository;
import com.github.pieter_groenendijk.domain.entities.account.Account;
import com.github.pieter_groenendijk.domain.entities.loan.LoanLimit;
import com.github.pieter_groenendijk.domain.entities.membership.Membership;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {
    @Mock
    private IAccountRepository accountRepository;
    @Mock
    MembershipRepository membershipRepository;
    @InjectMocks
    MembershipTypeRepository membershipTypeRepository;


    @InjectMocks
    private AccountService accountService;

    @Mock
    private Membership membership;

    @BeforeEach
    void setup() {
        accountRepository = Mockito.mock(IAccountRepository.class);
        MockitoAnnotations.openMocks(this);
        membership = new Membership();
        accountService = new AccountService(accountRepository, membershipTypeRepository, membershipRepository, null);
    }

    @Test
    void retrieveById_shouldReturnAccountIfExists() throws Exception {

        long accountId = 1L;
        Account account = new Account();
        account.setAccountId(accountId);

        when(accountRepository.retrieveAccountById(accountId)).thenReturn(Optional.of(account));


        Account retrievedAccount = accountService.retrieveAccountById(accountId);


        assertNotNull(retrievedAccount);
        assertEquals(accountId, retrievedAccount.getAccountId());
    }



}