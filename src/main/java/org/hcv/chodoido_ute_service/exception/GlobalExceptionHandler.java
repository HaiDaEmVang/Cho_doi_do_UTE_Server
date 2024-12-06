package org.hcv.chodoido_ute_service.exception;

import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> NotFoundException(NotFoundException ex){
        Error error = Error.builder()
                .error("Not found")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.ok().body(ResponseDTO.builder().error(error).status("error").build());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> AlreadyExistsException(AlreadyExistsException ex){
        Error error = Error.builder()
                .error("Already Exists")
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();
        return ResponseEntity.ok().body(ResponseDTO.builder().error(error).status("error").build());
    }

    @ExceptionHandler(NoActionException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> NoActionException(NoActionException ex){
        Error error = Error.builder()
                .error("No action")
                .message(ex.getMessage())
                .status(HttpStatus.ACCEPTED)
                .build();
        return ResponseEntity.ok().body(ResponseDTO.builder().error(error).status("error").build());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> MissingServletRequestParameterException(MissingServletRequestParameterException ex){
        Error error = Error.builder()
                .error("Input not valid")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.ok().body(ResponseDTO.builder().error(error).status("error").build());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public ResponseEntity<?> MaxUploadSizeExceededException(MaxUploadSizeExceededException ex){
        Error error = Error.builder()
                .error("Max upload size exceeded")
                .message(ex.getMessage())
                .status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
                .build();
        return ResponseEntity.ok().body(ResponseDTO.builder().error(error).status("error").build());
    }
  }
