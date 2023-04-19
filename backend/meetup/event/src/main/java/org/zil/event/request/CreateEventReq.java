package org.zil.event.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.zil.event.annotation.validator.EventDateConstraint;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateEventReq {

    @NotBlank(message = "Event name is required")
    private String title;

    @NotNull(message = "date and time of event is required")
    @EventDateConstraint()
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startAt;

    @NotNull(message = "Event duration in min. is needed")
    @Min(value = 5, message = "Duration(>=5) must be valid")
    private Integer duration;

    public LocalDateTime getStartAt() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(startAt, dateFormatter);
    }
}
