package org.zil.event;

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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

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

    @Column(name = "title", nullable = false, updatable = true)
    private String title;

    @Column(name = "event_start_at", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startAt;

    @Column(name = "event_end_at", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endAt;

    @Column(name = "duration_in_minute", nullable = false, updatable = true)
    private Integer duration;

    @Column(name = "ownership", nullable = false, updatable = true)
    private Integer ownership;

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
