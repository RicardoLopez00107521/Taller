package org.example.taller.handlers;

import lombok.extern.slf4j.Slf4j;
import org.example.taller.domain.dtos.GeneralResponse;
import org.example.taller.exceptions.LoginExceptions;
import org.example.taller.exceptions.RegisterExceptions;
import org.example.taller.utils.ErrorsTool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@Slf4j
@ControllerAdvice
public class GlobalErrorHandlers {

    private final ErrorsTool errorsTool;
    public GlobalErrorHandlers(ErrorsTool errorsTool) {
        this.errorsTool = errorsTool;
    }

    @ExceptionHandler(Exception.class)  // It is used to define which exception class will be handled
    public ResponseEntity<GeneralResponse> GeneralHandler(Exception ex) {
        log.error(ex.getMessage());
        log.error(ex.getClass().getCanonicalName());

        return GeneralResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @ExceptionHandler(NoResourceFoundException.class) // It is used to define which exception class will be handled
    public ResponseEntity<GeneralResponse> ResourceNotFound(NoResourceFoundException ex) {
        return GeneralResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // It is used to define which exception class will be handled
    public ResponseEntity<GeneralResponse> BadRequestHandler(MethodArgumentNotValidException ex) {
        return GeneralResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .data(errorsTool.mapErrors(ex.getBindingResult().getFieldErrors()))
                .build();
    }

    @ExceptionHandler(RegisterExceptions.class)
    public ResponseEntity<GeneralResponse> conflictException(RegisterExceptions ex) {
        return GeneralResponse.builder()
                .status(HttpStatus.CONFLICT)
                .data(ex.getMessage())
                .build();
    }

    @ExceptionHandler(LoginExceptions.class)
    public ResponseEntity<GeneralResponse> forbiddenException(LoginExceptions ex) {
        return GeneralResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .data(ex.getMessage())
                .build();
    }
}


