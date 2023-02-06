package com.example.crossFit.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {

    private String title;
    private String text;

    public MessageDTO(String title, String text) {
        this.title = title;
        this.text = text;
    }


}
