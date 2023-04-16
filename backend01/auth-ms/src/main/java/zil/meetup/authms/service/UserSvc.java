package zil.meetup.authms.service;

import org.springframework.stereotype.Service;
import zil.meetup.authms.dto.UserRegistrationReq;
import zil.meetup.authms.entity.User;
import zil.meetup.authms.vobj.AppUserRole;

@Service
public class UserSvc {

    public boolean createUser(UserRegistrationReq req) {
        String pwd = req.getPwd();
        String role = AppUserRole.CLIENT.toString();

        User user = User.builder()
                .email(req.getEmail())
                .phone(req.getPhone())
                .name(req.getName())
                .password(pwd)
                .role(role)
                .build();
        return true;
    }

}
