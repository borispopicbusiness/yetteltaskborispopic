package org.borispopic.yetteltask.controller.user.impl;

import org.borispopic.yetteltask.dto.UserDTO;
import org.borispopic.yetteltask.mapper.UserAndUserDTOMapper;
import org.borispopic.yetteltask.model.domain.User;
import org.borispopic.yetteltask.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserControllerImpl.class)
class UserControllerImplTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserAndUserDTOMapper userAndUserDTOMapper;

    @Test
    void createUser() throws Exception {
        User testCreatedUser = User.builder()
                .id(22)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthDate(new Date(1985, 8, 2))
                .phone("12345678")
                .build();

        UserDTO testCreatedUserDTO = UserDTO.builder()
                .id(22)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthDate(new Date(1985, 8, 2))
                .phone("12345678")
                .build();

        when(userService.createUser(any(User.class))).thenReturn(Optional.of(testCreatedUser));
        when(userAndUserDTOMapper.toUserDTO(any(User.class))).thenReturn(testCreatedUserDTO);
        when(userAndUserDTOMapper.toUser(any(UserDTO.class))).thenReturn(testCreatedUser = User.builder()
                .id(22)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthDate(new Date(1985, 8, 2))
                .phone("12345678")
                .build());

        mvc.perform(post("/api/v1/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":22,\"first_name\":\"TestFirstName\",\"last_name\":\"TestLastName\",\"birth_date\":\"1985-08-02\",\"phone\":\"12345678\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(22))
                .andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("TestFirstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.last_name").value("TestLastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("12345678"));
        ;

    }

    @Test
    void updateUser() throws Exception {
        User testCreatedUser = User.builder()
                .id(22)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthDate(new Date(1985, 8, 2))
                .phone("12345678")
                .build();

        UserDTO testCreatedUserDTO = UserDTO.builder()
                .id(22)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthDate(new Date(1985, 8, 2))
                .phone("12345678")
                .build();

        when(userService.updateUser(any(User.class))).thenReturn(Optional.of(testCreatedUser));
        when(userAndUserDTOMapper.toUserDTO(any(User.class))).thenReturn(testCreatedUserDTO);
        when(userAndUserDTOMapper.toUser(any(UserDTO.class))).thenReturn(testCreatedUser = User.builder()
                .id(22)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthDate(new Date(1985, 8, 2))
                .phone("12345678")
                .build());

        mvc.perform(put("/api/v1/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":22,\"first_name\":\"TestFirstName\",\"last_name\":\"TestLastName\",\"birth_date\":\"1985-08-02\",\"phone\":\"12345678\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(22))
                .andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("TestFirstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.last_name").value("TestLastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("12345678"));
        ;
    }

    @Test
    void ShouldThrow400Error() throws Exception {
        when(userService.deleteUser(anyLong())).thenReturn(Optional.empty());
        when(userAndUserDTOMapper.toUserDTO(isNull())).thenReturn(null);
        mvc.perform(delete("/api/v1/user/delete/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldBeSuccessful() throws Exception {
        when(userService.deleteUser(anyLong())).thenReturn(
                Optional.of(User.builder()
                        .id(22)
                        .firstName("TestFirstName")
                        .lastName("TestLastName")
                        .birthDate(new Date(1985, 8, 2))
                        .phone("12345678")
                        .build()
        ));
        when(userAndUserDTOMapper.toUserDTO(any(User.class))).thenReturn(UserDTO.builder()
                .id(22)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthDate(new Date(1985, 8, 2))
                .phone("12345678")
                .build()
        );
        mvc.perform(delete("/api/v1/user/delete/22"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldFetchUser() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(
                Optional.of(User.builder()
                        .id(22)
                        .firstName("TestFirstName")
                        .lastName("TestLastName")
                        .birthDate(new Date(1985, 8, 2))
                        .phone("12345678")
                        .build()
                )
        );

        when(userAndUserDTOMapper.toUserDTO(any(User.class))).thenReturn(
                UserDTO.builder()
                        .id(22)
                        .firstName("TestFirstName")
                        .lastName("TestLastName")
                        .birthDate(new Date(1985, 8, 2))
                        .phone("12345678")
                        .build()
        );

        mvc.perform(get("/api/v1/user/get/22"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}