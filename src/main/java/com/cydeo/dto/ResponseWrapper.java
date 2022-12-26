package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResponseWrapper {

    private boolean success;
    private Object data;
    private HttpStatus status;
    private String message;

    public ResponseWrapper(Object data, HttpStatus status, String message) {
        this.success = true;
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ResponseWrapper(Object data, String message) {
        this.success = true;
        this.data = data;
        this.message = message;
        this.status = HttpStatus.OK;
    }
}
