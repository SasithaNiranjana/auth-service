package com.uob.rd.authservice.service;

import com.uob.rd.authservice.entity.Account;

import java.util.Optional;

public interface AccountService {
    void saveAccount(Account account);
    Optional<Account> findAccountByUserName(String userName);
}
