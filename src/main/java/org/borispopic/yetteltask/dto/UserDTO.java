package org.borispopic.yetteltask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class UserDTO {
    @NotNull
    @JsonProperty("id")
    @JsonView(ResponseView.class)
    private long id;

    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("address")
    private String address;

    @NotNull
    @JsonProperty("phone")
    private String phone;

    @NotNull
    @JsonProperty("birth_date")
    private Date birthDate;

    @JsonView(ResponseView.class)
    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @JsonView(ResponseView.class)
    @JsonProperty("modified_date")
    private LocalDateTime modifiedDate;

    private interface ResponseView {
    }
}
