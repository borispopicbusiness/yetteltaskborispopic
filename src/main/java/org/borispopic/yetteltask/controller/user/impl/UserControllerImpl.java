package org.borispopic.yetteltask.controller.user.impl;

import lombok.extern.slf4j.Slf4j;
import org.borispopic.yetteltask.controller.user.UserController;
import org.borispopic.yetteltask.dto.UserDTO;
import org.borispopic.yetteltask.mapper.UserAndUserDTOMapper;
import org.borispopic.yetteltask.model.domain.User;
import org.borispopic.yetteltask.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAndUserDTOMapper userAndUserDTOMapper;

    @PostMapping("/create")
    @Override
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        log.info("/api/v1/user/create {user} triggered");

        Optional<User> createdUser = userService.createUser(userAndUserDTOMapper.toUser(user));

        if(createdUser.isPresent()) {
            log.info("/api/v1/user/create user created.");
        } else {
            log.info("/api/v1/user/create user not created");
        }

        return createdUser.map(value -> ResponseEntity.ok(
                userAndUserDTOMapper.toUserDTO(value))
        ).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PutMapping("/update")
    @Override
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
        log.info("/api/v1/user/update user updating");

        Optional<User> updatedUser = userService.updateUser(userAndUserDTOMapper.toUser(user));

        if(updatedUser.isPresent()){
            log.info("/api/v1/user/update user updated");
        } else {
            log.info("/api/v1/user/update user not updated");
        }

        return updatedUser.map( value -> ResponseEntity.ok(
                userAndUserDTOMapper.toUserDTO(value)
        )).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<UserDTO> deleteUser(@PathVariable(name = "id") long userId) {
        log.info("/api/v1/user/delete/" + userId +" triggered.");

        Optional<User> deletedUser = userService.deleteUser(userId);

        if(deletedUser.isPresent()) {
            log.info("/api/v1/user/get/" + userId +" deleted object");
        } else {
            log.info("/api/v1/user/get/" + userId +" found no object");
        }

        return deletedUser.map( value -> ResponseEntity.ok(
                userAndUserDTOMapper.toUserDTO(value)
        )).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @GetMapping("/get/{id}")
    @Override
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") long userId) {
        log.info("/api/v1/user/get/" + userId +" triggered.");

        Optional<User> foundUser = userService.getUser(userId);

        if (foundUser.isPresent()) {
            log.info("/api/v1/user/get/" + userId + " found object");
        } else {
            log.info("/api/v1/user/get/" + userId + " found no object");
        }

        return foundUser.map( value -> ResponseEntity.ok(userAndUserDTOMapper.toUserDTO(value)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
