package org.zil.user.dto.internals;

import javax.validation.constraints.NotBlank;

public record TokenValidationReq(@NotBlank(message = "Token is empty") String token) {
}
