package com.example.Natours.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse <T>{
    private String status;
    private T data;
    private String message;
    public ApiResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }
}
