package com.carsale.response;

import lombok.Data;

import java.util.List;

@Data
public class topUserResponse {
    private List<Integer> idList;
    private List<String> xList;
    private List<Integer> yList;
//    private List<???> source;
}
