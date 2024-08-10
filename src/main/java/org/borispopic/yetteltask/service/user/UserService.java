package org.borispopic.yetteltask.service.user;

import org.borispopic.yetteltask.model.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> createUser(User user);

    Optional<User> updateUser(User user);

    Optional<User> deleteUser(long userId);

    Optional<User> getUser(long userId);

}
