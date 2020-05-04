package com.mujiejun.domain;

import lombok.Data;

import java.util.List;

@Data
public class Menu {

    private List<Role> roles;
    private String url;
}
