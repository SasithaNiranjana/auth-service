package com.uob.rd.authservice.service;

import com.uob.rd.authservice.entity.Account;
import com.uob.rd.authservice.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public void saveAccount(Account account) {
        this.accountRepository.saveAndFlush(account);
    }

    @Override
    public Optional<Account> findAccountByUserName(String userName) {
        return this.accountRepository.findByUserName(userName);
    }
}
