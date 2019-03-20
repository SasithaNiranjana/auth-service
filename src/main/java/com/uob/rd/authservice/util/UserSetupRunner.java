package com.uob.rd.authservice.util;

import com.uob.rd.authservice.entity.Account;
import com.uob.rd.authservice.entity.Client;
import com.uob.rd.authservice.service.AccountService;
import com.uob.rd.authservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class UserSetupRunner implements CommandLineRunner {
    private final AccountService accountService;
    private final ClientService clientService;
    private final PasswordEncoder encoder;
    private final String roleString="USER,ADMIN";
    private final String grantTypesString="password,implicit";
    private final String scopeString="openid,otp";
    private final String resourceIdString="account-service,loan-service";

    public UserSetupRunner(AccountService accountService,ClientService clientService,PasswordEncoder encoder){
        this.accountService = accountService;
        this.clientService = clientService;
        this.encoder = encoder;
    }
    @Override
    public void run(String... args) throws Exception {
        Account account = new Account();
        account.setUserName("sasitha");
        account.setPassword(encoder.encode("Sasi123"));
        account.setActive(true);
        account.setAddress("Senkang");
        account.setAge(25);
        account.setRoles(Stream.of(roleString.split(",")).collect(Collectors.toSet()));
        this.accountService.saveAccount(account);
        log.info("Saved User");
        Client client = new Client();
        client.setClientId("acme");
        client.setClientSecret(encoder.encode("acmesecret"));
        client.setAuthorities(Stream.of(roleString.split(",")).collect(Collectors.toSet()));
        client.setGrantTypes(Stream.of(grantTypesString.split(",")).collect(Collectors.toSet()));
        client.setScopes(Stream.of(scopeString.split(",")).collect(Collectors.toSet()));
        client.setResourceIds(Stream.of(resourceIdString.split(",")).collect(Collectors.toSet()));
        this.clientService.saveClient(client);
        log.info("Saved Client");
    }
}
