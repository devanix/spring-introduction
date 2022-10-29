package com.jwlim.controller.domain;

import lombok.Data;

@Data
public class Member {

    private Long id;
    private String name;

    public Member() {

    }

    public Member(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
