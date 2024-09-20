package com.task2_2.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Integer id;
    private String username;
    private String password;
    private String role;
}
