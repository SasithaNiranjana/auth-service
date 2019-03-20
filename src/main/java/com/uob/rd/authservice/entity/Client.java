package com.uob.rd.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> scopes;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> grantTypes;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorities;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> resourceIds;
    private String clientId;
    private String clientSecret;
}
