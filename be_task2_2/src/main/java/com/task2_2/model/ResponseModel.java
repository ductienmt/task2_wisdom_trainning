package com.task2_2.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {
    private String status;
    private String timestamp;
    private Object message;
}
