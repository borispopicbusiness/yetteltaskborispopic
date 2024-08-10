package org.borispopic.yetteltask.model.domain;

import lombok.Builder;
import lombok.Data;
import org.borispopic.yetteltask.enums.AccountStatus;
import org.borispopic.yetteltask.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Account {
    private long id;
    private long ownerId;
    private String firstName;
    private String lastName;
    private BigDecimal balance;
    private BigDecimal minBalance;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String accountNumber;
    private AccountType accountType;
    private AccountStatus accountStatus;
}
