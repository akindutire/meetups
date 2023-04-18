package org.zil.user;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zil.user.dto.internals.UserLoginReq;
import org.zil.user.dto.internals.UserRegReq;
import org.zil.user.security.AppUserRole;
import org.zil.user.security.JWTSvc;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserSvc {

    private final UserRepo userRepo;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JWTSvc jwtSvc;

    public Map<String, Object> authenticate(UserLoginReq req) {
        Optional<User> userOp = userRepo.findByEmail(req.email());
        if (userOp.isPresent()) {
            User user = userOp.get();
            if (!bCryptPasswordEncoder.matches(req.password(), user.getPwd()))
                throw new BadCredentialsException("Login credentials is invalid");

            Map<String, Object> claim = new HashMap<>();

            List<String> l = new ArrayList<>();
            l.add(user.getRole().toString());

            claim.put("granted_authorities", l);

            String token = jwtSvc.generateToken(claim, user);
            claim.put("token", token);

            return claim;
        } else {
            throw new BadCredentialsException("Login credentials is invalid");
        }
    }
    public Map<String, Object>  register(UserRegReq req) {
        User user = User.builder()
                .pwd(bCryptPasswordEncoder.encode(req.getPassword()))
                .name(req.getName())
                .email(req.getEmail())
                .role(AppUserRole.CLIENT)
                .build();

        if(!req.getConfirmPassword().equals(req.getPassword()))
            throw new IllegalStateException("Password does not match password confirmation");

        if(userRepo.existsByEmail(req.getEmail()))
            throw new DuplicateKeyException("Email "+req.getEmail()+" is already in use");


        user.setCreatedAt();
        user.setModifiedAt();
        user.setEmailVerifiedAt(new Date());

        userRepo.saveAndFlush(user);

        Map<String, Object> claim = new HashMap<>();

        List<String> l = new ArrayList<>();
        l.add(user.getRole().toString());

        claim.put("granted_authorities", l);

        String token = jwtSvc.generateToken(claim, user);
        claim.put("token", token);
        claim.put("email", req.getEmail());
        claim.put("createdAt", user.getCreatedAt());

        return claim;
    }

    @NewSpan("finding-user")
    public Boolean isValid(Integer userId) {
        return userRepo.existsById(userId);
    }

}
