package org.zil.event.annotation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EventDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventDateConstraint {
    String message() default "Invalid event date time, it must be beyond now";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
