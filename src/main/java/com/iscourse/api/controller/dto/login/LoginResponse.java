package com.iscourse.api.controller.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String jwt;
    private String status;
    private CheckResponse checkResponse;
}
