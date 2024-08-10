package org.borispopic.yetteltask.mapper;

import org.borispopic.yetteltask.dto.AccountDTO;
import org.borispopic.yetteltask.model.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountAndAccountDTOMapper {
    @Mapping(target = ".", source = ".")
    AccountDTO toAccountDTO(Account accountEntity);

    @Mapping(target = ".", source = ".")
    Account toAccount(AccountDTO accountDto);
}
