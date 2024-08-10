package org.borispopic.yetteltask.service.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.borispopic.yetteltask.mapper.UserAndUserEntityMapper;
import org.borispopic.yetteltask.model.domain.User;
import org.borispopic.yetteltask.model.entity.UserEntity;
import org.borispopic.yetteltask.repository.UserRepository;
import org.borispopic.yetteltask.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAndUserEntityMapper userAndUserEntityMapper;

    @Override
    public Optional<User> createUser(User user) {
        if ( user != null ) {
            user.setCreatedDate(LocalDateTime.now());
            user.setModifiedDate(LocalDateTime.now());

            return Optional.of(userAndUserEntityMapper.toUser(
                    userRepository.save(userAndUserEntityMapper.toUserEntity(user))
            ));
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> updateUser(User user) {
        if ( user == null ) {
            return Optional.empty();
        }

        return userRepository.findById(user.getId())
                .map(foundUser -> updateUserDetails(user, foundUser))
                .map(updatedFoundUser -> userRepository.save(userAndUserEntityMapper.toUserEntity(updatedFoundUser)))
                .map(userAndUserEntityMapper::toUser);
    }

    @Override
    public Optional<User> deleteUser(long userId) {
        var foundUser = userRepository.findById(userId);

        if ( foundUser.isPresent() ) {
            userRepository.delete(foundUser.orElse(null));
            return Optional.of(userAndUserEntityMapper.toUser(foundUser.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUser(long userId) {
        var foundUser = userRepository.findById(userId);

        if ( foundUser.isPresent() )
            return foundUser.map(userAndUserEntityMapper::toUser);
        else
            return Optional.empty();
    }

    private User updateUserDetails(User user, UserEntity foundUser) {
        User updatedFoundUser = userAndUserEntityMapper.toUser(foundUser);
        updatedFoundUser.setModifiedDate(LocalDateTime.now());

        updateIfNotEmpty(user.getFirstName(), updatedFoundUser::setFirstName);
        updateIfNotEmpty(user.getMiddleName(), updatedFoundUser::setMiddleName);
        updateIfNotEmpty(user.getLastName(), updatedFoundUser::setLastName);
        updateIfNotEmpty(user.getEmail(), updatedFoundUser::setEmail);
        updateIfNotEmpty(user.getPhone(), updatedFoundUser::setPhone);
        updateIfNotEmpty(user.getAddress(), updatedFoundUser::setAddress);

        if ( user.getBirthDate() != null ) {
            updatedFoundUser.setBirthDate(user.getBirthDate());
        }

        return updatedFoundUser;
    }

    private void updateIfNotEmpty(String value, Consumer<String> setter) {
        if ( !StringUtils.isEmpty(value) ) {
            setter.accept(value);
        }
    }
}
