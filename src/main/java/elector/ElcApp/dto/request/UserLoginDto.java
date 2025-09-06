package elector.ElcApp.dto.request;

import lombok.Data;

@Data
public class UserLoginDto {
    private String username;
    private String password;
}