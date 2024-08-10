package org.borispopic.yetteltask.mapper;

import org.borispopic.yetteltask.model.domain.User;
import org.borispopic.yetteltask.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAndUserEntityMapper {
    @Mapping(target = ".", source = ".")
    User toUser(UserEntity userEntity);

    @Mapping(target = ".", source = ".")
    UserEntity toUserEntity(User user);
}
