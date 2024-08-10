package org.borispopic.yetteltask.controller.account.impl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.borispopic.yetteltask.controller.account.AccountController;
import org.borispopic.yetteltask.dto.AccountDTO;
import org.borispopic.yetteltask.mapper.AccountAndAccountDTOMapper;
import org.borispopic.yetteltask.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/account")
public class AccountControllerImpl implements AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountAndAccountDTOMapper accountAndAccountDTOMapper;

    @PostMapping("/create")
    @Override
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO account) {
        log.info("/api/v1/account/create triggered");

        return accountService.createAccount(accountAndAccountDTOMapper.toAccount(account))
                .map(value -> accountAndAccountDTOMapper.toAccountDTO(value))
                .map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/get/{id}")
    @Override
    public ResponseEntity<AccountDTO> getAccount(@PathVariable(name = "id") long userId) {
        log.info("/api/v1/account/get/{userId} triggered");

        var foundAccount = accountService.getAccount(userId);

        if ( foundAccount.isPresent() ) {
            log.info("/api/v1/account/get/{userId} found account");
        } else {
            log.info("/api/v1/account/get/{userId} found no account");
        }

        return foundAccount.map(value -> accountAndAccountDTOMapper.toAccountDTO(value))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
