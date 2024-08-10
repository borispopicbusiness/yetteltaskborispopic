package org.borispopic.yetteltask.controller.user;

import org.borispopic.yetteltask.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserController {
    ResponseEntity<UserDTO> createUser(UserDTO user);

    ResponseEntity<UserDTO> updateUser(UserDTO user);

    ResponseEntity<UserDTO> deleteUser(@PathVariable(name = "id") long userId);

    ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") long userId);

}
