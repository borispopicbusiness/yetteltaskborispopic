package org.borispopic.yetteltask.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.borispopic.yetteltask.enums.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Table(name = "accounts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "owner_id")
    private long ownerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "balance", precision = 19, scale = 4)
    private BigDecimal balance;

    @Column(name = "min_balance", precision = 19, scale = 4)
    private BigDecimal minBalance;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_status")
    private AccountStatus accountStatus;
}
