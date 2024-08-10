package org.borispopic.yetteltask.service.account.impl;

import org.borispopic.yetteltask.enums.AccountType;
import org.borispopic.yetteltask.mapper.AccountAndAccountEntityMapper;
import org.borispopic.yetteltask.model.domain.Account;
import org.borispopic.yetteltask.model.entity.AccountEntity;
import org.borispopic.yetteltask.repository.AccountRepository;
import org.borispopic.yetteltask.repository.UserRepository;
import org.borispopic.yetteltask.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountAndAccountEntityMapper accountAndAccountEntityMapper;

    @Override
    public Optional<Account> createAccount(Account account) {
        var ownerEntity = userRepository.findById(account.getOwnerId());

        if (ownerEntity.isEmpty())
            return Optional.empty();

        AccountEntity newAccount = accountAndAccountEntityMapper.toAccountEntity(account);

        newAccount.setFirstName(ownerEntity.get().getFirstName());
        newAccount.setLastName(ownerEntity.get().getLastName());
        newAccount.setOwnerId(ownerEntity.get().getId());
        newAccount.setCreatedDate(LocalDateTime.now());
        newAccount.setModifiedDate(LocalDateTime.now());
        newAccount.setMinBalance(newAccount.getBalance().multiply(BigDecimal.valueOf(0.05)));

        if (newAccount.getAccountType() == null) {
            newAccount.setAccountType(AccountType.CHECKING.getValue());
        }

        var createdAccount = accountRepository.save(newAccount);

        return Optional.of(accountAndAccountEntityMapper.toAccount(createdAccount));
    }

    @Override
    public Optional<Account> getAccount(long accountId) {
        var foundAccount = accountRepository.findById(accountId);
        return foundAccount.map(accountEntity -> accountAndAccountEntityMapper.toAccount(accountEntity));
    }
}
