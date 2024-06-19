package com.example.scaffolding.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
public class UserDto {

    private Long Id;

    @JsonProperty("username")
    private String UserName;

    @Email
    private String Email;
}
