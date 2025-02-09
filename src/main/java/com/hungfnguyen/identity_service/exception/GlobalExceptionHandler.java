package com.hungfnguyen.identity_service.exception;

import com.hungfnguyen.identity_service.dto.request.ApiReponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiReponse> handlingRunTimeException(RuntimeException exception){
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiReponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiReponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiReponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setCode(errorCode.getCode());
        apiReponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiReponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiReponse> handlingValidation(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try{
            errorCode = ErrorCode.valueOf(enumKey);
        } catch(IllegalArgumentException e){

        }
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setCode(errorCode.getCode());
        apiReponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiReponse);
    }


}
