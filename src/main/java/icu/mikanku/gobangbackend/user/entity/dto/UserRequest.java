package icu.mikanku.gobangbackend.user.entity.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
}
