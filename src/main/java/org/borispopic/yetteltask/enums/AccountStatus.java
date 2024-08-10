package org.borispopic.yetteltask.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    CLOSED("Closed");

    @JsonValue
    private final String value;

    @JsonCreator
    public static AccountStatus getAccountStatus(String accountStatus) {
        for (AccountStatus as : AccountStatus.values()) {
            if (as.getValue().equalsIgnoreCase(accountStatus)) {
                return as;
            }
        }

        throw new IllegalArgumentException("Unknown account status");
    }
}
