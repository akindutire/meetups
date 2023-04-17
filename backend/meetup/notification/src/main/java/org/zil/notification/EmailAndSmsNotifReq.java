package org.zil.notification;

import javax.validation.constraints.NotBlank;

enum NotifType {
    EMAIL,
    PHONE
}
public record EmailAndSmsNotifReq(@NotBlank String message, @NotBlank String type, @NotBlank String target) {
}
