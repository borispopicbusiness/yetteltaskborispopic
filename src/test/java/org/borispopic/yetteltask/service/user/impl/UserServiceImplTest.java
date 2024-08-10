package org.borispopic.yetteltask.service.user.impl;

import org.borispopic.yetteltask.mapper.UserAndUserEntityMapper;
import org.borispopic.yetteltask.model.domain.User;
import org.borispopic.yetteltask.model.entity.UserEntity;
import org.borispopic.yetteltask.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserAndUserEntityMapper userAndUserEntityMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private LocalDateTime tesCreatedLocalDateTime;
    private LocalDateTime tesUpdatedLocalDateTime;
    private Date testBirthdate;

    @BeforeEach
    void setUp() {
        tesCreatedLocalDateTime = LocalDateTime.now();
        tesUpdatedLocalDateTime = LocalDateTime.of(2024, Month.DECEMBER, 31, 6, 8, 0);
        testBirthdate = new Date(1985, 8, 2);
    }

    @Test
    void shouldCreateUser() {
        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(UserEntity.builder()
                        .id(1)
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .middleName("Test Middle Name")
                        .phone("Test Phone")
                        .email("Test Email")
                        .createdDate(tesCreatedLocalDateTime)
                        .modifiedDate(tesUpdatedLocalDateTime)
                        .birthDate(testBirthdate)
                        .build()
                );

        when(userAndUserEntityMapper.toUserEntity(any(User.class)))
                .thenReturn(UserEntity.builder()
                        .id(1)
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .middleName("Test Middle Name")
                        .phone("Test Phone")
                        .email("Test Email")
                        .createdDate(tesCreatedLocalDateTime)
                        .modifiedDate(tesUpdatedLocalDateTime)
                        .birthDate(testBirthdate)
                        .build()
                );

        when((userAndUserEntityMapper.toUser(any(UserEntity.class))))
                .thenReturn(User.builder()
                                .id(1)
                                .firstName("Test First Name")
                                .lastName("Test Last Name")
                                .middleName("Test Middle Name")
                                .phone("Test Phone")
                                .email("Test Email")
                                .createdDate(tesCreatedLocalDateTime)
                                .modifiedDate(tesUpdatedLocalDateTime)
                                .birthDate(testBirthdate)
                                .build()
                );

        User testUser = User.builder()
                .id(1)
                .firstName("Test First Name")
                .lastName("Test Last Name")
                .middleName("Test Middle Name")
                .phone("Test Phone")
                .email("Test Email")
                .createdDate(tesCreatedLocalDateTime)
                .modifiedDate(tesUpdatedLocalDateTime)
                .birthDate(testBirthdate)
                .build();

        var returnedUser = userService.createUser(testUser);
        assertNotNull(returnedUser.get(), "The returned created user should not be null");
        assertNotEquals(returnedUser.get(), testUser);
        assertEquals(returnedUser.get().getId(), testUser.getId());
        assertEquals(returnedUser.get().getFirstName(), testUser.getFirstName());
        assertEquals(returnedUser.get().getLastName(), testUser.getLastName());
    }

    @Test
    void shouldReturndUpdatedUser() {
        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(UserEntity.builder()
                        .id(1)
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .middleName("Test Middle Name")
                        .phone("Test Phone")
                        .email("Test Email")
                        .createdDate(tesCreatedLocalDateTime)
                        .modifiedDate(tesUpdatedLocalDateTime)
                        .birthDate(testBirthdate)
                        .build()
                );

        when(userAndUserEntityMapper.toUserEntity(any(User.class)))
                .thenReturn(UserEntity.builder()
                        .id(1)
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .middleName("Test Middle Name")
                        .phone("Test Phone")
                        .email("Test Email")
                        .createdDate(tesCreatedLocalDateTime)
                        .modifiedDate(tesUpdatedLocalDateTime)
                        .birthDate(testBirthdate)
                        .build()
                );

        when((userAndUserEntityMapper.toUser(any(UserEntity.class))))
                .thenReturn(User.builder()
                        .id(1)
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .middleName("Test Middle Name")
                        .phone("Test Phone")
                        .email("Test Email")
                        .createdDate(tesCreatedLocalDateTime)
                        .modifiedDate(tesUpdatedLocalDateTime)
                        .birthDate(testBirthdate)
                        .build()
                );
        when(userRepository.findById(anyLong())).thenReturn(
                Optional.of(UserEntity.builder()
                        .id(1)
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .middleName("Test Middle Name")
                        .phone("Test Phone")
                        .email("Test Email")
                        .createdDate(tesCreatedLocalDateTime)
                        .modifiedDate(tesUpdatedLocalDateTime)
                        .birthDate(testBirthdate)
                        .build()
                )
        );

        User testUser = User.builder()
                .id(1)
                .firstName("Test First Name")
                .lastName("Test Last Name")
                .middleName("Test Middle Name")
                .phone("Test Phone")
                .email("Test Email")
                .createdDate(tesCreatedLocalDateTime)
                .modifiedDate(tesUpdatedLocalDateTime)
                .birthDate(testBirthdate)
                .build();

        var returnedUser = userService.updateUser(testUser);
        assertNotNull(returnedUser.get(), "THe returned created user should not be null");
        assertNotEquals(returnedUser.get(), testUser);
        assertEquals(returnedUser.get().getId(), testUser.getId());
        assertEquals(returnedUser.get().getFirstName(), testUser.getFirstName());
        assertEquals(returnedUser.get().getLastName(), testUser.getLastName());
        assertNotEquals(tesUpdatedLocalDateTime, returnedUser.get().getModifiedDate());
    }

    @Test
    void shouldDeleteUser() {
        when(userRepository.findById(anyLong())).thenReturn(
                Optional.of(UserEntity.builder()
                        .id(1)
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .middleName("Test Middle Name")
                        .phone("Test Phone")
                        .email("Test Email")
                        .createdDate(tesCreatedLocalDateTime)
                        .modifiedDate(tesUpdatedLocalDateTime)
                        .birthDate(testBirthdate)
                        .build()
                )
        );

        when(userAndUserEntityMapper.toUser(any(UserEntity.class))
        ).thenReturn(User.builder()
                .id(1)
                .firstName("Test First Name")
                .lastName("Test Last Name")
                .middleName("Test Middle Name")
                .phone("Test Phone")
                .email("Test Email")
                .createdDate(tesCreatedLocalDateTime)
                .modifiedDate(tesUpdatedLocalDateTime)
                .birthDate(testBirthdate)
                .build()
        );

        userService.deleteUser(anyLong());

        verify(userRepository, times(1)).delete(any(UserEntity.class));
    }

    @Test
    void shouldFetchUser() {
        when(userRepository.findById(anyLong())).thenReturn(
                Optional.of(UserEntity
                        .builder()
                        .id(1)
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .middleName("Test Middle Name")
                        .phone("Test Phone")
                        .email("Test Email")
                        .createdDate(tesCreatedLocalDateTime)
                        .modifiedDate(tesUpdatedLocalDateTime)
                        .birthDate(testBirthdate)
                        .build()
                )
        );

        when(userAndUserEntityMapper.toUser(any(UserEntity.class))).thenReturn(
                User.builder()
                        .id(1)
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .middleName("Test Middle Name")
                        .phone("Test Phone")
                        .email("Test Email")
                        .createdDate(tesCreatedLocalDateTime)
                        .modifiedDate(tesUpdatedLocalDateTime)
                        .birthDate(testBirthdate)
                        .build()
        );

        var returnedObject = userService.getUser(anyLong());
        assertNotNull(returnedObject, "THe returned created user should not be null");
        assertEquals(returnedObject.get().getId(), 1);
        assertEquals(returnedObject.get().getFirstName(), "Test First Name");
        assertEquals(returnedObject.get().getLastName(), "Test Last Name");

        verify(userRepository, times(1)).findById(anyLong());
        verify(userAndUserEntityMapper, times(1)).toUser(any(UserEntity.class));
    }
}