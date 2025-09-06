package elector.ElcApp.dto.request;


import lombok.Data;

@Data
public class UserRegistrationDto {
    private String username;
    private String password;
    private String fullName;
}