package org.borispopic.yetteltask.service.account.impl;

import org.borispopic.yetteltask.enums.AccountStatus;
import org.borispopic.yetteltask.enums.AccountType;
import org.borispopic.yetteltask.mapper.AccountAndAccountEntityMapper;
import org.borispopic.yetteltask.model.domain.Account;
import org.borispopic.yetteltask.model.entity.AccountEntity;
import org.borispopic.yetteltask.model.entity.UserEntity;
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
        return userRepository.findById(account.getOwnerId())
                .map(ownerEntity -> {
                    AccountEntity newAccount = buildAccountEntity(account, ownerEntity);
                    AccountEntity savedAccount = accountRepository.save(newAccount);
                    return accountAndAccountEntityMapper.toAccount(savedAccount);
                });
    }

    @Override
    public Optional<Account> getAccount(long accountId) {
        var foundAccount = accountRepository.findById(accountId);
        return foundAccount.map(accountEntity -> accountAndAccountEntityMapper.toAccount(accountEntity));
    }

    private AccountEntity buildAccountEntity(Account account, UserEntity ownerEntity) {
        AccountEntity accountEntity = accountAndAccountEntityMapper.toAccountEntity(account);

        accountEntity.setFirstName(ownerEntity.getFirstName());
        accountEntity.setLastName(ownerEntity.getLastName());
        accountEntity.setOwnerId(ownerEntity.getId());
        accountEntity.setCreatedDate(LocalDateTime.now());
        accountEntity.setModifiedDate(LocalDateTime.now());
        accountEntity.setMinBalance(calculateMinBalance(accountEntity.getBalance()));
        accountEntity.setAccountNumber(accountNumberGenerator());
        accountEntity.setAccountStatus(AccountStatus.ACTIVE.getValue());

        if (accountEntity.getAccountType() == null) {
            accountEntity.setAccountType(AccountType.CHECKING.getValue());
        }

        return accountEntity;
    }

    private BigDecimal calculateMinBalance(BigDecimal balance) {
        return balance.multiply(BigDecimal.valueOf(0.05));
    }

    private String accountNumberGenerator() {
        StringBuilder accountNumber = new StringBuilder();

        for ( int i = 0; i < 20; i++ )
            accountNumber.append((int) (Math.random() * 10));

        return accountNumber.toString();
    }
}
