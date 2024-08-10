package org.borispopic.yetteltask.service.account.impl;

import org.borispopic.yetteltask.mapper.AccountAndAccountEntityMapper;
import org.borispopic.yetteltask.model.domain.Account;
import org.borispopic.yetteltask.model.entity.AccountEntity;
import org.borispopic.yetteltask.model.entity.UserEntity;
import org.borispopic.yetteltask.repository.AccountRepository;
import org.borispopic.yetteltask.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountAndAccountEntityMapper accountAndAccountEntityMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private LocalDateTime tesCreatedLocalDateTime;
    private LocalDateTime tesUpdatedLocalDateTime;
    private Date testBirthdate;

    @BeforeEach
    void setUp() {
        tesCreatedLocalDateTime = LocalDateTime.now();
        tesUpdatedLocalDateTime = LocalDateTime.of(2024, Month.DECEMBER, 31, 6, 8, 0);
        testBirthdate = new Date(1985, 8, 2);
    }

    @Test
    void shouldCreateAccount() {
        UserEntity testUserEntity = UserEntity.builder()
                .id(2)
                .birthDate(testBirthdate)
                .phone("123456789")
                .createdDate(tesCreatedLocalDateTime)
                .modifiedDate(tesUpdatedLocalDateTime)
                .email("test@test.com")
                .firstName("John")
                .lastName("Doe")
                .middleName("")
                .address("Address")
                .build();

        AccountEntity testAccountEntity = AccountEntity.builder()
                .id(1)
                .ownerId(testUserEntity.getId())
                .balance(BigDecimal.valueOf(300))
                .createdDate(tesCreatedLocalDateTime)
                .modifiedDate(tesUpdatedLocalDateTime)
                .minBalance(BigDecimal.valueOf(15))
                .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(testUserEntity));

        when(accountAndAccountEntityMapper.toAccount(any(AccountEntity.class)))
                .thenReturn(
                        Account.builder()
                                .id(1)
                                .ownerId(testUserEntity.getId())
                                .balance(BigDecimal.valueOf(300))
                                .createdDate(tesCreatedLocalDateTime)
                                .modifiedDate(tesUpdatedLocalDateTime)
                                .minBalance(BigDecimal.valueOf(15))
                                .build()
                );

        when(accountRepository.save(any(AccountEntity.class))).thenReturn(testAccountEntity);

        when(accountAndAccountEntityMapper.toAccountEntity(any(Account.class))).thenReturn(testAccountEntity);

        var returnedAccountObject = accountService.createAccount(Account.builder()
                .ownerId(testUserEntity.getId())
                .balance(BigDecimal.valueOf(300))
                .build());

        assertNotNull(returnedAccountObject.get());
        assertEquals(returnedAccountObject.get().getId(), returnedAccountObject.get().getId());
    }

    @Test
    void shouldFetchAccount() {
        when(accountRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        AccountEntity.builder()
                                .id(1)
                                .ownerId(3L)
                                .balance(BigDecimal.valueOf(300))
                                .build()
                )
        );

        when(accountAndAccountEntityMapper.toAccount(any(AccountEntity.class))).thenReturn(
                Account.builder()
                        .id(1)
                        .ownerId(3L)
                        .balance(BigDecimal.valueOf(300))
                        .build()
        );
        var returnedAccountObject = accountService.getAccount(1L);
        assertNotNull(returnedAccountObject.get());
        assertEquals(returnedAccountObject.get().getId(), returnedAccountObject.get().getId());
    }
}