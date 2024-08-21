package com.iscourse.api.controller.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckResponse {
    private Boolean isSignin;
    private Boolean isManager;
    private Boolean isAdmin;
}
