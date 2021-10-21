package com.mrd.identity.message;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageContent {

    private int status;
    private String message;
    private Object data;
    private Long total;

    public MessageContent(Object data) {
        this.status = data != null ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value();
        this.message = data != null ? HttpStatus.OK.toString() : HttpStatus.NOT_FOUND.toString();
        this.data = data;
    }

    public MessageContent(Object data, Long total) {
        this.status = data != null ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value();
        this.message = data != null ? HttpStatus.OK.toString() : HttpStatus.NOT_FOUND.toString();
        this.data = data;
        this.total = total;
    }

    public MessageContent(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String toJsonString(){
        return new Gson().toJson(this);
    }

}

