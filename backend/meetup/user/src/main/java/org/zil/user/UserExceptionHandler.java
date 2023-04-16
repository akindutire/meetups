package org.zil.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<?> handleDuplicateKeyException(DuplicateKeyException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.IM_USED);
        error.put("mode", "DUPLICATE_RECORD");
        error.put("exception", e.getClass().getName());

        log.info(Arrays.toString(e.getStackTrace()));

        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST);
        error.put("mode", "ILLEGAL_STATE_OF_REQUEST");
        error.put("exception", e.getClass().getName());

        log.info(Arrays.toString(e.getStackTrace()));

        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
