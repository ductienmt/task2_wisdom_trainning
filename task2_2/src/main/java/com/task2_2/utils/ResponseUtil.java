package com.task2_2.utils;

import com.task2_2.model.ResponseModel;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseUtil {
    public static final String SUCCESS = "Success";
    public static final String FAIL = "Fail";
    public static final String ERROR = "Error";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ResponseEntity<?> success(Object data) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(SUCCESS);
        responseModel.setMessage(data);
        responseModel.setTimestamp(LocalDateTime.now().format(formatter));
        return ResponseEntity.ok().body(responseModel);
    }

    public static ResponseEntity<?> fail(String data) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(FAIL);
        responseModel.setMessage(data);
        responseModel.setTimestamp(LocalDateTime.now().format(formatter));
        return ResponseEntity.badRequest().body(responseModel);
    }

    public static ResponseEntity<?> error() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(ERROR);
        responseModel.setMessage("Internal Server Error");
        responseModel.setTimestamp(LocalDateTime.now().format(formatter));
        return ResponseEntity.status(500).body(responseModel);
    }

    public static ResponseEntity<?> successAuth(String status, Object data) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(status);
        responseModel.setMessage(data);
        responseModel.setTimestamp(LocalDateTime.now().format(formatter));
        return ResponseEntity.ok().body(responseModel);
    }

    public static ResponseEntity<?> failAuth(String staus, String data) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(staus);
        responseModel.setMessage(data);
        responseModel.setTimestamp(LocalDateTime.now().format(formatter));
        return ResponseEntity.badRequest().body(responseModel);
    }
}
