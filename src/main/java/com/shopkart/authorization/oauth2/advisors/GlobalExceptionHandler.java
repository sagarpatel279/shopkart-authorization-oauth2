package com.shopkart.authorization.oauth2.advisors;

import com.shopkart.authorization.oauth2.exceptions.UserAlreadyExistException;
import com.shopkart.authorization.oauth2.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(UserAlreadyExistException uee) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(uee.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong..");
    }
}
