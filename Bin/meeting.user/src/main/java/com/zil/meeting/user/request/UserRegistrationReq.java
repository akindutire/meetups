package com.zil.meeting.user.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationReq {

    @NotBlank
    private String name;

    @NotBlank
    @Email(regexp = "^([A-Za-z]*[A-Za-z0-9_.-]+)@[A-Za-z0-9]+(\\.)([A-Za-z0-9_.]*[A-Za-z]+)$", message = "A valid email is required")
    private String email;
}
