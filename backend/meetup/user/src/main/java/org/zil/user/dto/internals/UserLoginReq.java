package org.zil.user.dto.internals;

import javax.validation.constraints.NotBlank;

public record UserLoginReq(@NotBlank(message = "Email is required") String email, @NotBlank(message = "Password is required") String password) {
}
