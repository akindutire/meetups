package org.zil.user;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserSvc {
    private final UserRepo userRepo;
    public UserSvc(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public Boolean register(UserReq req) {
        User user = User.builder()
                .pwd(req.getPassword())
                .name(req.getName())
                .email(req.getEmail())
                .build();

        if(!req.getConfirmPassword().equals(req.getPassword()))
            throw new IllegalStateException("Password does not match password confirmation");

        if(userRepo.findByEmail(req.getEmail()).isPresent())
            throw new DuplicateKeyException("Email "+req.getEmail()+" is already in use");


        user.setCreatedAt();
        user.setModifiedAt();
        user.setEmailVerifiedAt(new Date());

        userRepo.saveAndFlush(user);

        return true;
    }

    public Boolean isValid(Integer userId) {
        return userRepo.findById(userId).isPresent();
    }
}
