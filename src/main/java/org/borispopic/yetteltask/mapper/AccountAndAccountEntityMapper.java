package org.borispopic.yetteltask.mapper;

import org.borispopic.yetteltask.model.domain.Account;
import org.borispopic.yetteltask.model.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountAndAccountEntityMapper {
    @Mapping(target = ".", source = ".")
    Account toAccount(AccountEntity accountEntity);

    @Mapping(target = ".", source = ".")
    AccountEntity toAccountEntity(Account account);
}
