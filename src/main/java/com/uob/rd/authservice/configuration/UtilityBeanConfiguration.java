package com.uob.rd.authservice.configuration;

import com.uob.rd.authservice.service.AccountService;
import com.uob.rd.authservice.service.ClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.stream.Collectors;

@Configuration
public class UtilityBeanConfiguration {
    private final AccountService accountService;
    private final ClientService clientService;
    public UtilityBeanConfiguration(AccountService accountService,ClientService clientService){
        this.accountService = accountService;
        this.clientService = clientService;
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return userName->
                this.accountService
                .findAccountByUserName(userName)
                .map(account -> new User(account.getUserName(),account.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(account.getRoles().stream().collect(Collectors.joining(",")))))
                .orElseThrow(()-> new UsernameNotFoundException("User name cannot find"));
    }

    @Bean
    @Primary
    public ClientDetailsService clientDetailsService(){
        return clientId->
                this.clientService.findClientByClientId(clientId)
                .map(client -> {
                    BaseClientDetails baseClientDetails = new BaseClientDetails();
                    baseClientDetails.setClientId(client.getClientId());
                    baseClientDetails.setClientSecret(client.getClientSecret());
                    baseClientDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(client.getAuthorities().stream().collect(Collectors.joining(","))));
                    baseClientDetails.setScope(client.getScopes());
                    baseClientDetails.setAuthorizedGrantTypes(client.getGrantTypes());
                    baseClientDetails.setResourceIds(client.getResourceIds());
                    return baseClientDetails;
                }).orElseThrow(()->new ClientRegistrationException("Client id is not registered"));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
