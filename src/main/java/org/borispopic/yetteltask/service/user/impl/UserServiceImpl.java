package org.borispopic.yetteltask.service.user.impl;

import org.borispopic.yetteltask.mapper.UserAndUserEntityMapper;
import org.borispopic.yetteltask.model.domain.User;
import org.borispopic.yetteltask.repository.UserRepository;
import org.borispopic.yetteltask.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAndUserEntityMapper userAndUserEntityMapper;

    @Override
    public Optional<User> createUser(User user) {
        if(user != null){
            user.setCreatedDate(LocalDateTime.now());
            user.setModifiedDate(LocalDateTime.now());

            return Optional.of(userAndUserEntityMapper.toUser(
                    userRepository.save(
                            userAndUserEntityMapper.toUserEntity(user)
                    )
                )
            );
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> updateUser(User user) {
        if(user != null){
            user.setModifiedDate(LocalDateTime.now());

            return Optional.of(userAndUserEntityMapper.toUser(
                            userRepository.save(
                                    userAndUserEntityMapper.toUserEntity(user)
                            )
                    )
            );
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> deleteUser(long userId) {
        var foundUser = userRepository.findById(userId);

        if(foundUser.isPresent()){
            userRepository.delete(foundUser.orElse(null));
            return Optional.of(userAndUserEntityMapper.toUser(foundUser.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUser(long userId) {
        var foundUser = userRepository.findById(userId);

        if(foundUser.isPresent())
            return foundUser.map(userAndUserEntityMapper::toUser);
        else
            return Optional.empty();
    }

}
