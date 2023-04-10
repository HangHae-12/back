package com.sparta.finalproject.global.response;


import com.sparta.finalproject.global.dto.GlobalResponseDto;
import com.sparta.finalproject.global.response.exceptionType.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<GlobalResponseDto> handleUserException(UserException ex){
        CustomStatusCode statusCode = ex.getStatusCode();
        log.error(statusCode.getMessage());
        return ResponseEntity.ok(GlobalResponseDto.from(statusCode));
    }

    @ExceptionHandler(ImagePostException.class)
    public ResponseEntity<GlobalResponseDto> handleImagePostException(ImagePostException ex){
        CustomStatusCode statusCode = ex.getStatusCode();
        log.error(statusCode.getMessage());
        return ResponseEntity.ok(GlobalResponseDto.from(statusCode));
    }

    @ExceptionHandler(ClassroomException.class)
    public ResponseEntity<GlobalResponseDto> handleClassroomException(ClassroomException ex){
        CustomStatusCode statusCode = ex.getStatusCode();
        log.error(statusCode.getMessage());
        return ResponseEntity.ok(GlobalResponseDto.from(statusCode));
    }

    @ExceptionHandler(S3Exception.class)
    public ResponseEntity<GlobalResponseDto> handleS3Exception(S3Exception ex){
        CustomStatusCode statusCode = ex.getStatusCode();
        log.error(statusCode.getMessage());
        return ResponseEntity.ok(GlobalResponseDto.from(statusCode));
    }

    @ExceptionHandler(ChildException.class)
    public ResponseEntity<GlobalResponseDto> handleChildException(ChildException ex){
        CustomStatusCode statusCode = ex.getStatusCode();
        log.error(statusCode.getMessage());
        return ResponseEntity.ok(GlobalResponseDto.from(statusCode));
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<GlobalResponseDto> handleChildException(DateTimeException ex){
        CustomStatusCode statusCode = ex.getStatusCode();
        log.error(statusCode.getMessage());
        return ResponseEntity.ok(GlobalResponseDto.from(statusCode));
    }

    @ExceptionHandler(KindergartenException.class)
    public ResponseEntity<GlobalResponseDto> handleGlobalException(KindergartenException ex){
        CustomStatusCode statusCode = ex.getStatusCode();
        log.error(statusCode.getMessage());
        return ResponseEntity.ok(GlobalResponseDto.from(statusCode));
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<GlobalResponseDto> handleGlobalException(GlobalException ex){
        CustomStatusCode statusCode = ex.getStatusCode();
        log.error(statusCode.getMessage());
        return ResponseEntity.ok(GlobalResponseDto.from(statusCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDto> handleMethodException(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error(message);
        return ResponseEntity.ok(new GlobalResponseDto(HttpStatus.BAD_REQUEST.value(), message, null));
    }

    @ExceptionHandler(AttendanceException.class)
    public ResponseEntity<GlobalResponseDto> handleAttendanceException(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error(message);
        return ResponseEntity.ok(new GlobalResponseDto(HttpStatus.BAD_REQUEST.value(), message, null));
    }
}
