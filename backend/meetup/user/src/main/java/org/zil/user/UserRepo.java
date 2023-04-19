package org.zil.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u.id User u WHERE u.email=?1")
    Optional<Integer> findByEmailOnly(String email);

    Optional<User> findByEmailAndPwd(String email, String password);

    Boolean existsByEmail(String email);
}
