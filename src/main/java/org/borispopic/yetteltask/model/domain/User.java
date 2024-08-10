package org.borispopic.yetteltask.model.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class User {
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private Date birthDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
