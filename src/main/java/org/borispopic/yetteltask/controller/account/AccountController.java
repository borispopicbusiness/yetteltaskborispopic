package org.borispopic.yetteltask.controller.account;

import org.borispopic.yetteltask.dto.AccountDTO;
import org.springframework.http.ResponseEntity;

public interface AccountController {
    ResponseEntity<AccountDTO> createAccount(AccountDTO account);

    ResponseEntity<AccountDTO> getAccount(long userId);
}
