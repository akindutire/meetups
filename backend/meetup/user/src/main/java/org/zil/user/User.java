package org.zil.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

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
}
