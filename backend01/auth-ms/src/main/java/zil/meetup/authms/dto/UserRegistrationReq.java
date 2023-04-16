package zil.meetup.authms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationReq {
    private String name;
    private String email;
    private String phone;
    private String pwd;
    private String confirm_pwd;
    private String role;
}
