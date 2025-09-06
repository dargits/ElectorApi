package elector.ElcApp.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Integer id;
    private String username;
    private String fullName;
    private Boolean isAdmin;
}