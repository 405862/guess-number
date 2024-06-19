package com.example.scaffolding.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Setter
@Getter
@NoArgsConstructor
public class UserModel {

    private Long id;

    private String userName;

    private String email;

}
