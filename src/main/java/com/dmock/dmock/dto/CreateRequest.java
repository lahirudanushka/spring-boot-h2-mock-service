package com.dmock.dmock.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateRequest {
    private String packageName;
    private Object body;
    private Integer responseCode;
    private List<String> supportMethods;
    private String mediaType;
}
