package com.ssafy.happyhouse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private int userSeq;
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userPhone;
    private int userRoleId; //공백시 default 2(normal)
}
