package com.focuz.authservice.infrastructure.persistence.database.entity.clientscope;

public interface ClientScopeDao {
    Long getClientScopeId();
    Long getClientId();
    Long getScopeId();
    String getClientCode();
    String getClientSecret();
    String getRedirectUri();
    String getScopeCode();
    String getScopeName();
}
