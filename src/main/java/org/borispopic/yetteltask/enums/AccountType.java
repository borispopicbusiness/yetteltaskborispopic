package org.borispopic.yetteltask.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    SAVINGS("SAVINGS"),
    BUSINESS("BUSINESS"),
    CHECKING("CHECKING");

    @JsonValue
    private final String value;

    @JsonCreator
    public static AccountType getAccountType(String accountType) {
        for (AccountType at : AccountType.values()) {
            if (at.getValue().equalsIgnoreCase(accountType)) {
                return at;
            }
        }
        throw new IllegalArgumentException("Undefined account type");
    }
}
