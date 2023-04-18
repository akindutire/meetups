package org.zil.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.zil.user.security.AppUserRole;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @JsonIgnore
    @Id
    @SequenceGenerator(
        name = "user_id_sequence",
        sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    private Integer id;

    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    @JsonIgnore
    @Column(name = "password", nullable = false, updatable = true)
    private String pwd;

    @Column(name = "email_verified_at", nullable = true, updatable = true)
    private Date emailVerifiedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column(name = "modified_at", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt = new Date();

    public LocalDateTime getCreatedAt(){
        Instant t = this.createdAt.toInstant();

        return LocalDateTime.ofInstant(t, ZoneId.of("Africa/Lagos"));
    }

    public LocalDateTime getModifiedAt(){
        Instant t = this.modifiedAt.toInstant();

        return LocalDateTime.ofInstant(t, ZoneId.of("Africa/Lagos"));
    }

    public void setModifiedAt(){
        this.modifiedAt = new Date();
    }

    public void setCreatedAt() {
        this.createdAt = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
