package com.example.cosmeticapi.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NotNull
@Validated
public class UserLoginDTO {
    @Length(min = 6, max = 12, message = "Username muse be between 1 and 12 characters")
    @NotNull(message = "Username muse be not empty")
    private String username;

    @Length(min = 8, max = 12, message = "Username muse be between 1 and 12 characters")
    @NotNull(message = "Username muse be not empty")
    private String password;
}
