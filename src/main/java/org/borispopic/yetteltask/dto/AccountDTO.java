package org.borispopic.yetteltask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.borispopic.yetteltask.enums.AccountStatus;
import org.borispopic.yetteltask.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AccountDTO {
    @NotNull
    @JsonView(ResponseAccountDTO.class)
    @JsonProperty("id")
    private long id;

    @NotNull
    @JsonProperty("owner_id")
    private long ownerId;

    @NotNull
    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonView(ResponseAccountDTO.class)
    @JsonProperty("first_name")
    private String firstName;

    @JsonView(ResponseAccountDTO.class)
    @JsonProperty("last_name")
    private String lastName;

    @JsonView(ResponseAccountDTO.class)
    @JsonProperty("min_balance")
    private BigDecimal minBalance;

    @JsonView(ResponseAccountDTO.class)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonView(ResponseAccountDTO.class)
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonView(ResponseAccountDTO.class)
    @JsonProperty("account_number")
    private String accountNumber;

    @JsonView(ResponseAccountDTO.class)
    @JsonProperty("account_type")
    private AccountType accountType;

    @JsonView(ResponseAccountDTO.class)
    @JsonProperty("status")
    private AccountStatus accountStatus;

    private interface ResponseAccountDTO {
    }
}
