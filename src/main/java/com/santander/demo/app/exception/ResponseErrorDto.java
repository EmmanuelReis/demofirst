package com.santander.demo.app.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseErrorDto {
    

    private List<ErrorMessage> errors;

    @Data
    @AllArgsConstructor
    public class ErrorMessage {

        private String message;
    }
}
