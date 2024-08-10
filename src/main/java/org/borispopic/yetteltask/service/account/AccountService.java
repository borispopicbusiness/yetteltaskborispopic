package org.borispopic.yetteltask.service.account;

import org.borispopic.yetteltask.model.domain.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> createAccount(Account account);

    Optional<Account> getAccount(long accountId);
}
