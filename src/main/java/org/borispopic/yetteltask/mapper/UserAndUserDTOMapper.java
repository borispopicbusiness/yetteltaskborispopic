package org.borispopic.yetteltask.mapper;

import org.borispopic.yetteltask.dto.UserDTO;
import org.borispopic.yetteltask.model.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAndUserDTOMapper {
    @Mapping(target = ".", source = ".")
    UserDTO toUserDTO(User user);

    @Mapping(target = ".", source = ".")
    User toUser(UserDTO userDTO);
}
